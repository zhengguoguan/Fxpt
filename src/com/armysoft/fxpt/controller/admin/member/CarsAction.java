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
import com.armysoft.fxpt.model.Address;
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









@Controller
@RequestMapping("admin/cars")
public class  CarsAction extends BaseController {
	@Resource
	private AddressService arService;
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
	/**
	 * 条件分页查询
	 * @param currentPage
	 * @param model
	 * @param entity
	 * @param request
	 * @return
	 */
    @RequestMapping(value = PAGE_LIST)    
	public String getByPage(@PathVariable Integer currentPage,String fcpbh,String fcdname,String fhymc,String frysjf,String frysje, String fhtqxf,String fhtqxe,String cyqy,String hylbNo,String hyzcNo,String ssq,String fzjgNo,Model model,
			CdCategories entity, HttpServletRequest request) {
		Pagination pager = initPage(currentPage);
		pager.setPageSize(10);
		Map<String, Object> params = new HashMap<String, Object>();
		model.addAttribute("list", service.getByPage(params, pager));
		model.addAttribute("page", pager);
		model.addAttribute("model", entity);
		return "admin/member/CdInformationQ";
	}
    //商品加进购物车
    
    @RequestMapping(value = "/toCars.html")
	@ResponseBody
	public String toCars(HttpServletRequest request,HttpServletResponse response) throws ParseException {
    	JSONObject jsonObject = new JSONObject();
    	String id=request.getParameter("id");
    	String num=request.getParameter("num");
    	System.out.println(id+num);
    	HttpSession session = request.getSession();
    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
    	Cars Fcar=null;
    	if(snsUserInfo!=null){
       Fcar=service.findByOpid(Integer.valueOf(id), snsUserInfo.getOpenId());
        }
        if(Fcar==null){
        	Cars cars =new Cars();
        	cars.setCreatedate(new Date());
        	cars.setPid(Integer.valueOf(id));
        	cars.setPnum(Integer.valueOf(num));
        	cars.setOpenid(snsUserInfo.getOpenId());
        	service.insert(cars);
        }else{
        	Fcar.setPnum(Fcar.getPnum()+Integer.valueOf(num));
        	service.update(Fcar);
        }
        jsonObject.put("exl","ok");
		response.setContentType("text/html;charset=UTF-8");   
		 try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
    
    /*@RequestMapping(value = "/changeCars.html")
	public String changeCars(String id,String num,HttpServletRequest request,HttpServletResponse response) throws ParseException {
    	JSONObject jsonObject = new JSONObject();
    	
    	String[] ids=id.split(",");
    	String[] nums=num.split(",");
    	
    	for(int i=0;i<ids.length;i++){
    		Cars car=service.findByKey(Long.valueOf(ids[i]));
    		car.setPnum(Integer.valueOf(nums[i]));
    		service.update(car);
    	}
    	
    	HttpSession session = request.getSession();
    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
    	//String openid="okETVtxhhU46yChDM4jM-xSCws08";
    	 	String openid=snsUserInfo.getOpenId();
    	  Address ads=  arService.findByCheckOd(openid);
    	request.setAttribute("listcars", service.findByOd(openid));
    	request.setAttribute("adds", arService.findByCheckOd(openid));
    	return "index/toorders";
	}*/
    
    
  /*  @RequestMapping(value = "/listCars.html")
	public  String listCars(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	HttpSession session = request.getSession();
    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
    //		String openid="";
  	    String openid=snsUserInfo.getOpenId();
   	    List list=service.findByOd(openid);
   	    request.setAttribute("listsize", service.findByOd(openid).size());
    	request.setAttribute("listcars", service.findByOd(openid));
    	return "index/listcars";
	}*/
    
   /* @RequestMapping(value = "/toOrders.html")
	public  String toOrders(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	HttpSession session = request.getSession();
    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
    	 //  	String openid="okETVtxhhU46yChDM4jM-xSCws08";
    	 	 String openid=snsUserInfo.getOpenId();
    	
    	request.setAttribute("listcars", service.findByOd(openid));
    	request.setAttribute("adds", arService.findByOd(openid));
    	
    	return "index/toorders";
	}*/
    

    @RequestMapping(value = "/Index.html")
	public  String Index(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	return "index/index";
	}

    
    
	/**
	 * 详情/准备修改
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = DETAIL)
	public String detail(@PathVariable("id") Long key, Model model,HttpServletRequest request) {
		model.addAttribute("model", service.findByKey(key));
		return "index/listcars";
	}
    
	
	/**
	 * 准备添加
	 * @return
	 */

	

	/**
	 * 保存
	 * @param entity
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Integer key,Cars entity, @RequestParam("files") MultipartFile[] files,String oldfiles,String oldchfiles,Model model, HttpServletRequest request) throws IllegalStateException, IOException {
        service.update(entity);
		return "redirect:/admin/cdInformation/list/1.html";
	}
	@RequestMapping(value = SAVE)
	public String save(Cars entity, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		if (entity.getId() == null) {
		    service.insert(entity);
		} else {
			service.update(entity);
		}
		return "redirect:/admin/cdInformation/list/1.html";
	}
	
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key) {
		service.delete(key);
		return "index/listcars";
	}
	/*@RequestMapping(value = "/removeCars.html")
	public String removeCars(Long id,HttpServletRequest request,HttpServletResponse response) {
		service.delete(id);
		HttpSession session = request.getSession();
    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
    	//String openid="";
    	 	 String openid=snsUserInfo.getOpenId();
    	 request.setAttribute("listsize", service.findByOd(openid).size());
    	request.setAttribute("listcars", service.findByOd(openid));
		return "index/listcars";
	}*/
	

}
