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
import com.armysoft.fxpt.model.CdCategories;
import com.armysoft.fxpt.model.CdInformation;
import com.armysoft.fxpt.model.CdSmallclass;
import com.armysoft.fxpt.model.Orders;
import com.armysoft.fxpt.model.ProductCate;
import com.armysoft.fxpt.service.member.CdCategoriesService;
import com.armysoft.fxpt.service.member.CdInformationService;
import com.armysoft.fxpt.service.member.CdSmallclassService;
import com.armysoft.fxpt.service.member.OrdersService;
import com.armysoft.fxpt.service.member.ProductCateService;
import com.csvreader.CsvReader;









@Controller
@RequestMapping("admin/orders") 
public class  OrdersAction extends BaseController {

	@Resource
	private OrdersService service;
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
    
    
    @RequestMapping(value = "/cdIndex.html")
	@ResponseBody
	public  String cdIndex(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	String idStr = request.getParameter("id");
    	String key = request.getParameter("key");
    	String pStr = request.getParameter("p");
    	int p = 1;
    	if (pStr!=null && pStr!="") {
    	      p = Integer.parseInt(pStr);
    	     }
    	 Pagination pager = initPage(p);
		 pager.setPageSize(10);
		 Map<String, Object> params = new HashMap<String, Object>();
		 if(idStr !="" && idStr!=null){
				params.put("idStr", idStr);
				request.setAttribute("idStr", idStr); 
		    }
		 if(key !="" && key!=null){
				params.put("key", key);
				request.setAttribute("key", key);
		    }
    	 List cdList= service.getByPage(params, pager);
		 JSONObject jsonObject = new JSONObject();
		  if (cdList.size() == 0)
    	      {
    	        	jsonObject.put("status", "0");
    	        	jsonObject.put("isNextPage", "0");
    	      }
    	      else {
    	    	  jsonObject.put("status", "1");
    	      if (cdList.size() == pager.getPageSize())
    	        {
    	    	  jsonObject.put("isNextPage", "1");
    	        }
    	        else {
    	        	jsonObject.put("isNextPage", "0");
    	        }
   	         JSONArray listJson = (JSONArray)JSONArray.toJSON(cdList);
    	     jsonObject.put("list", listJson);
    	      }
		 response.setContentType("text/html;charset=UTF-8");   
		 try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
    @RequestMapping(value = "/toadd.html")
    @ResponseBody
	public  void toadd(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	String pidStr = request.getParameter("pid");
    	                int pid = 0;
    	                try {
    	                pid = Integer.parseInt(pidStr);
    	                } catch (Exception e) {
    	                request.setAttribute("status", "0");
    	                request.setAttribute("message", "参数错误");
    	                try {
    	                request.getRequestDispatcher("/WEB-INF/jsp/index/cart.jsp").forward(request,response);
    	                } catch (ServletException e1) {
    	                e1.printStackTrace();
    	                } catch (IOException e1) {
    	                e1.printStackTrace();
    	                }
    	               return;
    	               }
    	              CdInformation findProduct = (CdInformation)cdService.findByKey(Long.valueOf(pid));
    	               if (findProduct == null) {
    	            	   request.setAttribute("status", "0");
    	            	   request.setAttribute("message", "商品不存在");
    	               } else {
    	                request.setAttribute("status", "1");
    	                request.setAttribute("product", findProduct);
    	               }
    	            try {
    	             request.getRequestDispatcher("/WEB-INF/jsp/index/cart.jsp").forward(request, response);
    	              } catch (ServletException e) {
    	              e.printStackTrace();
    	             } catch (IOException e) {
    	             e.printStackTrace();
    	             }
    	       
	}

    @RequestMapping(value = "/Index.html")
	public  String Index(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
    	return "index/index";
	}
//    @RequestMapping(value = "/Topay.html")
//	public  String Topay(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
//    	//网页授权后获取传递的参数
//		String userId = request.getParameter("userId"); 	
//		String orderNo = request.getParameter("orderNo"); 	
//		String money = request.getParameter("money");
//		String code = request.getParameter("code");
//		//金额转化为分为单位
//		float sessionmoney = Float.parseFloat(money);
//		String finalmoney = String.format("%.2f", sessionmoney);
//		finalmoney = finalmoney.replace(".", "");
//		
////商户相关资料 
//		String appid = "";
//		String appsecret = "";
//		String partner = "";
//		String partnerkey = "";
//		
//		
//		String openId ="";
//		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		HttpResponse temp = HttpConnect.getInstance().doGetStr(URL);		
//		String tempValue="";
//		if( temp == null){
//				response.sendRedirect("/weChatpay/error.jsp");
//		}else
//		{
//			try {
//				tempValue = temp.getStringResult();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			JSONObject  jsonObj = JSONObject.fromObject(tempValue);
//			if(jsonObj.containsKey("errcode")){
//				System.out.println(tempValue);
//				response.sendRedirect("/weChatpay/error.jsp");
//			}
//			openId = jsonObj.getString("openid");
//		}
//		
//		
//		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
//				String currTime = TenpayUtil.getCurrTime();
//				//8位日期
//				String strTime = currTime.substring(8, currTime.length());
//				//四位随机数
//				String strRandom = TenpayUtil.buildRandom(4) + "";
//				//10位序列号,可以自行调整。
//				String strReq = strTime + strRandom;
//				
//				
//				//商户号
//				String mch_id = partner;
//				//子商户号  非必输
////				String sub_mch_id="";
//				//设备号   非必输
//				String device_info="";
//				//随机数 
//				String nonce_str = strReq;
//				//商品描述
//                //				String body = describe;
//				
//                //商品描述根据情况修改
//				String body = "美食";
//				//附加数据
//				String attach = userId;
//				//商户订单号
//				String out_trade_no = orderNo;
//				int intMoney = Integer.parseInt(finalmoney);
//				
//				//总金额以分为单位，不带小数点
//				int total_fee = intMoney;
//				//订单生成的机器 IP
//				String spbill_create_ip = request.getRemoteAddr();
//				//订 单 生 成 时 间   非必输
////				String time_start ="";
//				//订单失效时间      非必输
////				String time_expire = "";
//				//商品标记   非必输
////				String goods_tag = "";
//				
//				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
//				String notify_url ="http://192.168.1.111:8082/testPay/aa.htm";
//				
//				
//				String trade_type = "JSAPI";
//				String openid = openId;
//				//非必输
////				String product_id = "";
//				SortedMap<String, String> packageParams = new TreeMap<String, String>();
//				packageParams.put("appid", appid);  
//				packageParams.put("mch_id", mch_id);  
//				packageParams.put("nonce_str", nonce_str);  
//				packageParams.put("body", body);  
//				packageParams.put("attach", attach);  
//				packageParams.put("out_trade_no", out_trade_no);  
//				
//				
//				//这里写的金额为1 分到时修改
//				packageParams.put("total_fee", "1");  
////				packageParams.put("total_fee", "finalmoney");  
//				packageParams.put("spbill_create_ip", spbill_create_ip);  
//				packageParams.put("notify_url", notify_url);  
//				
//				packageParams.put("trade_type", trade_type);  
//				packageParams.put("openid", openid);  
//
//				RequestHandler reqHandler = new RequestHandler(request, response);
//				reqHandler.init(appid, appsecret, partnerkey);
//				
//				String sign = reqHandler.createSign(packageParams);
//				String xml="<xml>"+
//						"<appid>"+appid+"</appid>"+
//						"<mch_id>"+mch_id+"</mch_id>"+
//						"<nonce_str>"+nonce_str+"</nonce_str>"+
//						"<sign>"+sign+"</sign>"+
//						"<body><![CDATA["+body+"]]></body>"+
//						"<attach>"+attach+"</attach>"+
//						"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
//						"<attach>"+attach+"</attach>"+
//	//金额，这里写的1 分到时修改
//						"<total_fee>"+1+"</total_fee>"+
////						"<total_fee>"+finalmoney+"</total_fee>"+
//						"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
//						"<notify_url>"+notify_url+"</notify_url>"+
//						"<trade_type>"+trade_type+"</trade_type>"+
//						"<openid>"+openid+"</openid>"+
//						"</xml>";
//				System.out.println(xml);
//				String allParameters = "";
//				try {
//					allParameters =  reqHandler.genPackage(packageParams);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//				Map<String, Object> dataMap2 = new HashMap<String, Object>();
//				String prepay_id="";
//				try {
//					prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);
//					if(prepay_id.equals("")){
//						request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
//						response.sendRedirect("/weChatpay/error.jsp");
//					}
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				SortedMap<String, String> finalpackage = new TreeMap<String, String>();
//				String appid2 = appid;
//				String timestamp = Sha1Util.getTimeStamp();
//				String nonceStr2 = nonce_str;
//				String prepay_id2 = "prepay_id="+prepay_id;
//				String packages = prepay_id2;
//				finalpackage.put("appId", appid2);  
//				finalpackage.put("timeStamp", timestamp);  
//				finalpackage.put("nonceStr", nonceStr2);  
//				finalpackage.put("package", packages);  
//				finalpackage.put("signType", "MD5");
//				String finalsign = reqHandler.createSign(finalpackage);
//				
//				response.sendRedirect("/weChatpay/pay.jsp?appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonceStr2+"&package="+packages+"&sign="+finalsign);
//	}
    
    
	/**
	 * 详情/准备修改
	 * @param key
	 * @param model
	 * @return
	 */
	@RequestMapping(value = DETAIL)
	public String detail(@PathVariable("id") Long key, Model model,HttpServletRequest request) {
		model.addAttribute("model", service.findByKey(key));
		return "admin/member/CdInformationV";
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
/*	@RequestMapping(value = UPDATE)
	public String update(@PathVariable("id") Integer key,Orders entity, @RequestParam("files") MultipartFile[] files,String oldfiles,String oldchfiles,Model model, HttpServletRequest request) throws IllegalStateException, IOException {
        service.update(entity);
		return "redirect:/admin/cdInformation/list/1.html";
	}
	@RequestMapping(value = SAVE)
	public String save(Orders entity, @RequestParam("files") MultipartFile[] files, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		if (entity.getId() == null) {
		    service.insert(entity);
		} else {
			service.update(entity);
		}
		return "redirect:/admin/cdInformation/list/1.html";
	}*/
	public static String convertDate(Date date) {
		SimpleDateFormat todayDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return todayDateFormatter.format(date);
	}
	 public static String getRealPath2(){
	    	try{
	    		String classPath = OrdersAction.class.getClassLoader().getResource("/").getPath();
	    		  String rootPath  = "";
	    		  //windows下
	    		  if("\\".equals(File.separator)){   
	    		   rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
	    		   rootPath = rootPath.replace("/", "\\");
	    		  }
	    		  //linux下
	    		  if("/".equals(File.separator)){   
	    		   rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
	    		   rootPath = rootPath.replace("\\", "/");
	    		  }
	    		  rootPath=rootPath.replaceAll("%20", " ");
	    		  return rootPath;
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	    	}
	    	return "";
	    }
	/**
	 * 删除
	 * @param key
	 * @return
	 */
/*	@RequestMapping(value = DELETE)
	public String delete(@PathVariable("id") Long key) {
		service.delete(key);
		return "redirect:/admin/cdInformation/list/1.html";
	}*/
	
	

}
