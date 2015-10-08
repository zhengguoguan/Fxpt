package com.armysoft.fxpt.controller.admin.wechatpay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.armysoft.springmvc.controller.BaseController;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armysoft.fxpt.model.Address;
import com.armysoft.fxpt.model.Cars;
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
@RequestMapping("portal/pubv3pay")
public class  PubV3PayController extends BaseController {
	
	@Resource
	private OrdersDetailService ordersDetailService;
	@Resource
	private OrdersService ordersService;
	@Resource
	private AddressService addressService;
	@Resource
	private CarsService carsService;
	
	@RequestMapping(value = "/payrecall.html")
	public void payrecall(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	        InputStream inStream = request.getInputStream();
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        while ((len = inStream.read(buffer)) != -1) {
	            outSteam.write(buffer, 0, len);
	        }
	        System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
	        outSteam.close();
	        inStream.close();
	        String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
	        Map<Object, Object> map = XMLUtil.doXMLParse(result);
	        for(Object keyValue : map.keySet()){
	            System.out.println(keyValue+"="+map.get(keyValue));
	        }
	        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
	            //TODO 对数据库的操作
	            response.getWriter().write(PayCommonUtil.setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
	            System.out.println("-------------"+PayCommonUtil.setXML("SUCCESS", ""));
	        }
		
	}
	
	@RequestMapping(value = "/payOffsuccess.html")
	public String payOffsuccess(HttpServletRequest request,HttpServletResponse response)   {
		
		
		 return "/portal/PaySuccess";
	}
	@RequestMapping(value = "/paysuccess.html")
	public String paysuccess(HttpServletRequest request,HttpServletResponse response,String out_trade_no,String randCode)   {
		//更改订单状态为已支付
		ordersService.updateByOrderId(out_trade_no,randCode);
		
		 return "/portal/PaySuccess";
	}
	
	@RequestMapping(value = "/payOffExecute.html")
	@ResponseBody()
	public String payOffExecute(HttpServletRequest request,HttpServletResponse response,Integer totalPrice)  {
		try{	
			 HttpSession session = request.getSession();
			 SNSUserInfo sui=(SNSUserInfo)session.getAttribute("snsUserInfo");
			 SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String out_trade_no= sFormat.format(new Date());
			  //插入订单记录与订单细目记录
		      Address address=addressService.findByCheckOd(sui.getOpenId());
		      
		      Orders order=new Orders(out_trade_no, sui.getOpenId(),address.getLxrxm(),address.getProvno()+address.getCityno()+address.getXxdz(),address.getLxrdh(),totalPrice,1);
		    
		    
		      List<Map<String, Object>> cars= carsService.findByOpenId(sui.getOpenId());
		      ordersDetailService.batchInsert(cars, order);     //订单记录与订单细目记录事务提交,同时删除相应的购物车记录
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping(value = "/payExecute.html")
	@ResponseBody()
	public String payExecute(HttpServletRequest request,HttpServletResponse response,Integer totalPrice) throws JDOMException, IOException  {
	/*	String openid2="okETVt03KpFheLrWsY6G7aUS-Lm0";
		Orders order2=new Orders("ff",openid2,"ff","ff","ff",0,3,"ff");
		    
		    
	      List<Map<String, Object>> cars2= carsService.findByOpenId(openid2);
	      ordersDetailService.batchInsert(cars2, order2);     //订单记录与订单细目记录事务提交
*/		
		//String mchid="1264677701";
		try{
			 HttpSession session = request.getSession();
			 SNSUserInfo sui=(SNSUserInfo)session.getAttribute("snsUserInfo");
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			parameters.put("appid", ConfigUtil.APPID);

			parameters.put("mch_id", ConfigUtil.MCH_ID);
			parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
			parameters.put("body", "订单支付测试");
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			parameters.put("out_trade_no", sFormat.format(new Date())); //"201412051510111"精确到毫秒
			//Integer totalPrice=Integer.parseInt(String.valueOf(Float.parseFloat(request.getParameter("totalPrice").toString())*100));
			//Integer totalPrice=Integer.parseInt(request.getParameter("totalPrice").toString())*100;
			//int a=Float.parseFloat(totalPrice)*100;
			parameters.put("total_fee",String.valueOf(totalPrice)) ;//Float.parseFloat(totalPrice)*100
			String spbill_create_ip=NetworkUtil.getIpAddress(request);
			parameters.put("spbill_create_ip",spbill_create_ip);
			System.out.println("spbill_create_ip:"+spbill_create_ip);
			parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid", sui.getOpenId());
			String sign = PayCommonUtil.createSign("UTF-8", parameters);
			parameters.put("sign", sign);
			System.out.println("sign:"+sign);
			
			String requestXML = PayCommonUtil.getRequestXml(parameters);
			System.out.println("requestXML:"+requestXML);
			String result =CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
			System.out.println("result:"+result);
			Map<String, String> map = XMLUtil.doXMLParse(result);//解析微信返回的信息，以Map形式存储便于取值
			SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	        params.put("appId", ConfigUtil.APPID);
	        params.put("timeStamp", Long.toString(new Date().getTime()).substring(0,10));
	        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
	        params.put("package", "prepay_id="+map.get("prepay_id"));
	        params.put("signType", ConfigUtil.SIGN_TYPE);
	        String paySign =  PayCommonUtil.createSign("UTF-8", params);
	        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
	        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
	        params.put("sendUrl", ConfigUtil.SUCCESS_URL);                               //付款成功后跳转的页面
	        String userAgent = request.getHeader("user-agent");
	        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
	        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
	        
	        JsApiTicket jst=null; //有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
	        if(session.getAttribute("jsApiTicket")!=null){
	        	jst=(JsApiTicket)session.getAttribute("jsApiTicket");
	        }else{
	        	//获取signature
	            Token tk=CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APP_SECRECT);
	            jst= CommonUtil.getjsApiTicket(tk.getAccessToken());
	            request.getSession().setAttribute("jsApiTicket", jst);
	        }
	       
	        String url=request.getParameter("url").toString();
	        String signature=  PayCommonUtil.createSignature(jst.getTicket(), params.get("timeStamp").toString(), params.get("nonceStr").toString(),url );
	      params.put("signature", signature.toLowerCase());   
	      
	     //插入订单记录与订单细目记录
	      Address address=addressService.findByCheckOd(sui.getOpenId());
	      String randCode=String.valueOf(Math.random());
	      Orders order=new Orders(parameters.get("out_trade_no").toString(), sui.getOpenId(),address.getLxrxm(),address.getProvno()+address.getCityno()+address.getXxdz(),address.getLxrdh(),0,totalPrice,randCode,0);
	    
	    
	      List<Map<String, Object>> cars= carsService.findByOpenId(sui.getOpenId());
	      ordersDetailService.batchInsert(cars, order);     //订单记录与订单细目记录事务提交,同时删除相应的购物车记录
	      
	      params.put("randCode", randCode);
	      params.put("out_trade_no", parameters.get("out_trade_no").toString());
	        String json = JSONObject.fromObject(params).toString();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	      
		
	}
	
	@RequestMapping(value = "/payHisOrder.html")
	@ResponseBody()
	public String payHisOrder( Long id,HttpServletRequest request,HttpServletResponse response)   {

		
		try{
			
			Map<String, Object>  hisOrder=ordersService.findByKey(id);
			
			
			 HttpSession session = request.getSession();
			 SNSUserInfo sui=(SNSUserInfo)session.getAttribute("snsUserInfo");
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			parameters.put("appid", ConfigUtil.APPID);

			parameters.put("mch_id", ConfigUtil.MCH_ID);
			parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
			parameters.put("body", "历史订单支付测试");
			String currentTime=String.valueOf(new Date().getTime());
			parameters.put("out_trade_no", hisOrder.get("OrderId").toString()+"_"+currentTime.substring(7)); //这样可以避免出现商户订单号重复
			
			parameters.put("total_fee",hisOrder.get("Price").toString()) ;//Float.parseFloat(totalPrice)*100
			String spbill_create_ip=NetworkUtil.getIpAddress(request);
			parameters.put("spbill_create_ip",spbill_create_ip);
			System.out.println("spbill_create_ip:"+spbill_create_ip);
			parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid",  hisOrder.get("OpenId").toString());
			String sign = PayCommonUtil.createSign("UTF-8", parameters);
			parameters.put("sign", sign);
			System.out.println("sign:"+sign);
			
			String requestXML = PayCommonUtil.getRequestXml(parameters);
			System.out.println("requestXML:"+requestXML);
			String result =CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
			System.out.println("result:"+result);
			Map<String, String> map = XMLUtil.doXMLParse(result);//解析微信返回的信息，以Map形式存储便于取值
			SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	        params.put("appId", ConfigUtil.APPID);
	        params.put("timeStamp", Long.toString(new Date().getTime()).substring(0,10));
	        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
	        params.put("package", "prepay_id="+map.get("prepay_id"));
	        params.put("signType", ConfigUtil.SIGN_TYPE);
	        String paySign =  PayCommonUtil.createSign("UTF-8", params);
	        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
	        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
	        params.put("sendUrl", ConfigUtil.SUCCESS_URL);                               //付款成功后跳转的页面
	        String userAgent = request.getHeader("user-agent");
	        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
	        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
	        
	        JsApiTicket jst=null; //有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
	        if(session.getAttribute("jsApiTicket")!=null){
	        	jst=(JsApiTicket)session.getAttribute("jsApiTicket");
	        }else{
	        	//获取signature
	            Token tk=CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APP_SECRECT);
	            jst= CommonUtil.getjsApiTicket(tk.getAccessToken());
	            request.getSession().setAttribute("jsApiTicket", jst);
	        }
	       
	        String url=request.getParameter("url").toString();
	        String signature=  PayCommonUtil.createSignature(jst.getTicket(), params.get("timeStamp").toString(), params.get("nonceStr").toString(),url );
	      params.put("signature", signature.toLowerCase());   
	      
	     // String randCode=String.valueOf(Math.random());
	      params.put("randCode", hisOrder.get("RandCode").toString());
	      params.put("out_trade_no",hisOrder.get("OrderId").toString());
	        String json = JSONObject.fromObject(params).toString();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	      
		
	}
	
	@RequestMapping(value = "/payExecuteTest.html")
	@ResponseBody()
	public String payExecuteTest(HttpServletRequest request,HttpServletResponse response,Integer totalPrice)  {

		try{
			 HttpSession session = request.getSession();
			 SNSUserInfo sui=(SNSUserInfo)session.getAttribute("snsUserInfo");
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			parameters.put("appid", ConfigUtil.APPID);

			parameters.put("mch_id", ConfigUtil.MCH_ID);
			parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
			parameters.put("body", "订单支付测试");
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			parameters.put("out_trade_no", sFormat.format(new Date())); //"201412051510111"精确到毫秒
			//Integer totalPrice=Integer.parseInt(String.valueOf(Float.parseFloat(request.getParameter("totalPrice").toString())*100));
			//Integer totalPrice=Integer.parseInt(request.getParameter("totalPrice").toString())*100;
			//int a=Float.parseFloat(totalPrice)*100;
			parameters.put("total_fee",String.valueOf(totalPrice)) ;//Float.parseFloat(totalPrice)*100
			String spbill_create_ip=NetworkUtil.getIpAddress(request);
			parameters.put("spbill_create_ip",spbill_create_ip);
			System.out.println("spbill_create_ip:"+spbill_create_ip);
			parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid", sui.getOpenId());
			String sign = PayCommonUtil.createSign("UTF-8", parameters);
			parameters.put("sign", sign);
			System.out.println("sign:"+sign);
			
			String requestXML = PayCommonUtil.getRequestXml(parameters);
			System.out.println("requestXML:"+requestXML);
			String result =CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
			System.out.println("result:"+result);
			Map<String, String> map = XMLUtil.doXMLParse(result);//解析微信返回的信息，以Map形式存储便于取值
			SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	        params.put("appId", ConfigUtil.APPID);
	        params.put("timeStamp", Long.toString(new Date().getTime()).substring(0,10));
	        params.put("nonceStr", PayCommonUtil.CreateNoncestr());
	        params.put("package", "prepay_id="+map.get("prepay_id"));
	        params.put("signType", ConfigUtil.SIGN_TYPE);
	        String paySign =  PayCommonUtil.createSign("UTF-8", params);
	        params.put("packageValue", "prepay_id="+map.get("prepay_id"));    //这里用packageValue是预防package是关键字在js获取值出错
	        params.put("paySign", paySign);                                                          //paySign的生成规则和Sign的生成规则一致
	        params.put("sendUrl", ConfigUtil.SUCCESS_URL);                               //付款成功后跳转的页面
	        String userAgent = request.getHeader("user-agent");
	        char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
	        params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
	        
	        JsApiTicket jst=null; //有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket
	        if(session.getAttribute("jsApiTicket")!=null){
	        	jst=(JsApiTicket)session.getAttribute("jsApiTicket");
	        }else{
	        	//获取signature
	            Token tk=CommonUtil.getToken(ConfigUtil.APPID,ConfigUtil.APP_SECRECT);
	            jst= CommonUtil.getjsApiTicket(tk.getAccessToken());
	            request.getSession().setAttribute("jsApiTicket", jst);
	        }
	       
	        String url=request.getParameter("url").toString();
	        String signature=  PayCommonUtil.createSignature(jst.getTicket(), params.get("timeStamp").toString(), params.get("nonceStr").toString(),url );
	      params.put("signature", signature.toLowerCase());   
	      
	     //插入订单记录与订单细目记录
	      Address address=addressService.findByCheckOd(sui.getOpenId());
	      String randCode=String.valueOf(Math.random());
	      Orders order=new Orders(parameters.get("out_trade_no").toString(), sui.getOpenId(),address.getLxrxm(),address.getProvno()+address.getCityno()+address.getXxdz(),address.getLxrdh(),0,totalPrice,randCode,0);
	    
	    
	      List<Map<String, Object>> cars= carsService.findByOpenId(sui.getOpenId());
	      ordersDetailService.batchInsert(cars, order);     //订单记录与订单细目记录事务提交
	      
	      params.put("randCode", randCode);
	      params.put("out_trade_no", parameters.get("out_trade_no").toString());
	        String json = JSONObject.fromObject(params).toString();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	      
		
	}
	public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
	}
/*	public static String getjsTicket(String accesstoken) {
        String appid = ConfigUtil.APPID;
        String appsecret = ConfigUtil.APP_SECRECT;
        String result = "";
        String url = js_ticketurl.replace("ACCESS_TOKEN", accesstoken);
        System.out.println("查看js_url:" + url);
        // 调用接口返回json字符串
        JSONObject jsonObject = httpRequest(url, "GET", "");
        System.out.println("查看红的js_ticket:" + jsonObject.toString());
        if (null != jsonObject) {
            result = jsonObject.getString("ticket");// 获得ticket
            System.out.println("ticket为:" + result);
        }
        return result;
    }*/

}
