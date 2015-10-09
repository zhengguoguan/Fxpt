package com.armysoft.fxpt.controller.admin.member;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Case;
import org.armysoft.core.Pagination;
import org.armysoft.springmvc.controller.BaseController;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.armysoft.security.annotation.PermissionsAnno;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.armysoft.fxpt.base.common.ConfigUtil;
import com.armysoft.fxpt.base.common.PayCommonUtil;
import com.armysoft.fxpt.base.common.Sha1Util;
import com.armysoft.fxpt.base.common.TenpayUtil;
import com.armysoft.fxpt.base.common.XMLUtil;
import com.armysoft.fxpt.base.util.CSVUtils;
import com.armysoft.fxpt.cache.AppCache;
import com.armysoft.fxpt.model.Cars;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.Orders;
import com.armysoft.fxpt.model.ProductCate;
import com.armysoft.fxpt.pojo.SNSUserInfo;
import com.armysoft.fxpt.service.member.CarsService;
import com.armysoft.fxpt.service.member.CdCategoriesService;
import com.armysoft.fxpt.service.member.CdInformationService;
import com.armysoft.fxpt.service.member.CdSmallclassService;
import com.csvreader.CsvReader;
import com.armysoft.fxpt.service.member.AddressService;
import com.armysoft.fxpt.model.Address;








@Controller
@RequestMapping("admin/address")
public class  AddressAction extends BaseController {
	@Resource
	private AddressService addressService;
	@Resource
	private CarsService service;
	@Resource
	private CdInformationService cdService;
	
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }  
	
	
	 @RequestMapping(value = "/addAddress.html")
		public  String addAddress(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
	    	return "index/addAddress";
		}
	 
	 @RequestMapping(value = "/listAddress.html")
		public  String listAddress(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		    HttpSession session = request.getSession();
	    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
	    	//String openid="okETVt03KpFheLrWsY6G7aUS-Lm0";
	    	String openid=snsUserInfo.getOpenId();
	   	    List list=addressService.findByOd(openid);
	   	    request.setAttribute("listsize", addressService.findByOd(openid).size());
	    	request.setAttribute("listaddress", addressService.findByOd(openid));
	    	return "index/listAddress";
		}
	 
	 @RequestMapping(value = "/changeAddress.html")
		public String changeAddress(String id,String checkvalue,HttpServletRequest request,HttpServletResponse response) throws ParseException {
	    	JSONObject jsonObject = new JSONObject();
	    	
	    	String[] checks=checkvalue.split(",");
	    	String[] ids=id.split(",");
	    	
	    	for(int i=0;i<ids.length;i++){
	    		Address ads=addressService.findByKey(Long.valueOf(ids[i]));
	    		ads.setSeltype(checks[i]);
	    		addressService.update(ads);
	    	}
	    	
	    	
	    	HttpSession session = request.getSession();
	    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
	    	//String openid="okETVt03KpFheLrWsY6G7aUS-Lm0";
	    	String openid=snsUserInfo.getOpenId();
	   	    List list=addressService.findByOd(openid);
	   	    request.setAttribute("listsize", addressService.findByOd(openid).size());
	    	request.setAttribute("listaddress", addressService.findByOd(openid));
	    	return "index/listAddress";
		}
	 
	 
	 @RequestMapping(value = SAVE)
		public String save(Address entity, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
			if (entity.getId() == null) {
				
				entity.setCreatedate(new Date());
				HttpSession session = request.getSession();
		    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
		    	//String openid="";
		    	String openid=snsUserInfo.getOpenId();
		    	List list=addressService.findByOd(openid);
		    	if(list==null){
		    		entity.setSeltype("1");
		    		
		    	}
		    	entity.setOpenid(openid);
				addressService.insert(entity);
			} else {
				addressService.update(entity);
			}
			HttpSession session = request.getSession();
	    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
	    		//String openid="okETVt03KpFheLrWsY6G7aUS-Lm0";
	    		 String openid=snsUserInfo.getOpenId();
	    	
	    	request.setAttribute("listcars", service.findByOd(openid));
	    	request.setAttribute("adds", addressService.findByOd(openid));
	    	return "index/toorders";
		}
}
