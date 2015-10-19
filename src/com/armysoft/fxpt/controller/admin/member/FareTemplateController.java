package com.armysoft.fxpt.controller.admin.member;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.armysoft.core.Pagination;
import org.armysoft.springmvc.controller.BaseController;
import org.jdom.JDOMException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armysoft.fxpt.model.Address;
import com.armysoft.fxpt.model.Cars;
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.Orders;
import com.armysoft.fxpt.pojo.JsApiTicket;
import com.armysoft.fxpt.pojo.SNSUserInfo;
import com.armysoft.fxpt.pojo.Token;
import com.armysoft.fxpt.service.member.AddressService;
import com.armysoft.fxpt.service.member.CarsService;
import com.armysoft.fxpt.service.member.CdInformationService;
import com.armysoft.fxpt.service.member.OrdersDetailService;
import com.armysoft.fxpt.service.member.OrdersService;
import com.armysoft.fxpt.wechatpay.CommonUtil;
import com.armysoft.fxpt.wechatpay.ConfigUtil;
import com.armysoft.fxpt.wechatpay.NetworkUtil;
import com.armysoft.fxpt.wechatpay.PayCommonUtil;
import com.armysoft.fxpt.wechatpay.XMLUtil;




@Controller("fareTemplateController")
@RequestMapping("admin/fareTemplate")
public class  FareTemplateController extends BaseController {
	
	@Resource
	private OrdersDetailService ordersDetailService;
	@Resource
	private OrdersService ordersService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	  @RequestMapping(value = PAGE_LIST)    
		public String getByPage(@PathVariable Integer currentPage,Model model,String tag,Date beginTime,Date endTime,String payType,
			 String orderId,HttpServletRequest request) {
		  
			Pagination pager = initPage(currentPage);
			pager.setPageSize(10);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tag", tag);
			params.put("beginTime", beginTime);
			params.put("endTime", endTime);
			params.put("payType", payType);
			params.put("orderId", orderId);
			
			model.addAttribute("list", ordersService.getByPage(params, pager));
			model.addAttribute("page", pager);
			model.addAttribute("params", params);
			return "admin/member/OrderQ";
		}
	  @RequestMapping(value = ADD)
		public String add() {
		 
		  
			return "admin/member/FareTemplateA_U";
		}
	  @RequestMapping(value = SAVE)
		public String save(HttpServletRequest request) {
		  Enumeration<String> paraNames=request.getParameterNames();
		  for(Enumeration e=paraNames;e.hasMoreElements();){
		   
		         String thisName=e.nextElement().toString();
		         
		         String thisValue=request.getParameter(thisName);
		         System.out.println(thisName+"--------------"+thisValue);
		   
		  }

		  
		  
			return "admin/member/FareTemplateA_U";
		}
	  
	  
	  /**
	   * 取消订单
	   * @param key
	   * @return
	   */
	  @RequestMapping(value = DELETE)
		public String delete(@PathVariable("id") Long key) {
		 
		  ordersService.delete(key);
			return "redirect:/admin/order/list/1.html?tag=0";
		}
	  @RequestMapping(value = DETAIL)
		public String detail(@PathVariable("id") Long key, Model model,HttpServletRequest request) {
		  model.addAttribute("model", ordersService.findByKey(key));
		  return "admin/member/OrderD";
		}
	  
	
}
