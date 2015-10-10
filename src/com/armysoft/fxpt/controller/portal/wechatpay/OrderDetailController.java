package com.armysoft.fxpt.controller.portal.wechatpay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
@RequestMapping("portal/orderDetail")
public class  OrderDetailController extends BaseController {
	
	@Resource
	private OrdersDetailService ordersDetailService;
	@Resource
	private OrdersService ordersService;

	
	  @RequestMapping(value = PAGE_LIST)    
		public String getByPage(@PathVariable Integer currentPage,Model model,String orderId,
			 HttpServletRequest request) {
			Pagination pager = initPage(currentPage);
			pager.setPageSize(10);
			
			
			model.addAttribute("order", ordersService.findByOrderId(orderId));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("orderId", orderId);
			model.addAttribute("list", ordersDetailService.getByPage(params, pager));
			model.addAttribute("page", pager);
			
			return "portal/OrderDetailQ";
		}
	  

}
