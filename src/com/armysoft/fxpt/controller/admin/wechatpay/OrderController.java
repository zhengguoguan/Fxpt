package com.armysoft.fxpt.controller.admin.wechatpay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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




@Controller
@RequestMapping("portal/order")
public class  OrderController extends BaseController {
	@Resource
	private AddressService arService;
	@Resource
	private CarsService carsService;
	@Resource
	private OrdersDetailService ordersDetailService;
	@Resource
	private OrdersService ordersService;

	
	  @RequestMapping(value = PAGE_LIST)    
		public String getByPage(@PathVariable Integer currentPage,Model model,String tag,
			 HttpServletRequest request) {
		  // String openId="okETVt03KpFheLrWsY6G7aUS-Lm0";
		  HttpSession session = request.getSession();
			 SNSUserInfo sui=(SNSUserInfo)session.getAttribute("snsUserInfo");
			Pagination pager = initPage(currentPage);
			pager.setPageSize(10);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tag", tag);
			params.put("openId", sui.getOpenId());
			//params.put("openId", openId);
			model.addAttribute("list", ordersService.getByPage(params, pager));
			model.addAttribute("page", pager);
			model.addAttribute("tag", tag);
			return "portal/OrderQ";
		}
	  /**
	   * 取消订单
	   * @param key
	   * @return
	   */
	  @RequestMapping(value = DELETE)
		public String delete(@PathVariable("id") Long key) {
		  Map<String, Object> params = new HashMap<String, Object>();
			params.put("tag", 2);
			params.put("id", key);
		  ordersService.updateTag(params);
			return "redirect:/portal/order/list/1.html?tag=0";
		}
	  @RequestMapping(value = "/detail.html")
		public String detail(String orderId,Model model) {
		  model.addAttribute("order", ordersService.findByOrderId(orderId));
		
			model.addAttribute("list", ordersDetailService.findAllByOrderId(orderId));
			return "portal/OrderDetailQ";
		}
	  
	  @RequestMapping(value = "/orderSure.html")
		public  String orderSure(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
	    	HttpSession session = request.getSession();
	    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
	    	//String openid=snsUserInfo.getOpenId();

	    	 String openid="okETVtxhhU46yChDM4jM-xSCws08";
	    	List<Cars>  listcars=carsService.findByOd(openid);
	    	request.setAttribute("listcars", listcars);

	    	request.setAttribute("adds", arService.findByCheckOd(openid));
	    	
	    	return "portal/OrderSure";
		}
		@RequestMapping(value = "/orderTest.html")
		public String paydoing(HttpServletRequest request,HttpServletResponse response)   {
			 return "portal/OrderTest";
		}
	
		@RequestMapping(value = "/changeCars.html")
		public String changeCars(String id,String num,HttpServletRequest request,HttpServletResponse response) throws ParseException {
	    	JSONObject jsonObject = new JSONObject();
	    	if(num!="" && num!=null){
	    	String[] ids=id.split(",");
	    	String[] nums=num.split(",");
	    	
	    	for(int i=0;i<ids.length;i++){
	    		Cars car=carsService.findByKey(Long.valueOf(ids[i]));
	    		car.setPnum(Integer.valueOf(nums[i]));
	    		carsService.update(car);
	    	}
	    	}
	    	HttpSession session = request.getSession();
	    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
	    	String openid="okETVtxhhU46yChDM4jM-xSCws08";
	    	//String openid=snsUserInfo.getOpenId();
	    	  Address ads=  arService.findByCheckOd(openid);
	    	request.setAttribute("listcars", carsService.findByOd(openid));
	    	request.setAttribute("adds", arService.findByCheckOd(openid));
	    	return "portal/OrderSure";
		}
		  @RequestMapping(value = "/listCars.html")
			public  String listCars(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		    	HttpSession session = request.getSession();
		    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");

		    	  String openid="okETVtxhhU46yChDM4jM-xSCws08";
		    	  //  String openid=snsUserInfo.getOpenId();

		   	    List list=carsService.findByOd(openid);
		   	    request.setAttribute("listsize", carsService.findByOd(openid).size());
		    	request.setAttribute("listcars", carsService.findByOd(openid));
		    	return "portal/listcars";
			}
		  @RequestMapping(value = "/removeCars.html")
			public String removeCars(Long id,HttpServletRequest request,HttpServletResponse response) {
			  carsService.delete(id);
				HttpSession session = request.getSession();
		    	SNSUserInfo snsUserInfo =(SNSUserInfo) session.getAttribute("snsUserInfo");
		    	String openid="";
		    	// String openid=snsUserInfo.getOpenId();
		    	 request.setAttribute("listsize", carsService.findByOd(openid).size());
		    	request.setAttribute("listcars", carsService.findByOd(openid));
				return "portal/listcars";
			}
}
