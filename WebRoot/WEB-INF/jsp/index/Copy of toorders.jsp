<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>芸祥文具</title>
<link href="${pageContext.request.contextPath}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/theme/style/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="UTF-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
var totalPrice=0; //总金额
$(function(){
	        	$("#addAddress").attr("href","${ctx}/admin/address/listAddress.html");
	        	setTotal();
});

function setTotal(){ 
    var s=0; 
    $(".cart_items").each(function(){ 
		 s+=parseInt($(this).find('span[class*=min_text_box]').text())
             *parseFloat($(this).find('span[class*=money]').text()); 
		 }); 
    totalPrice=s.toFixed(2);
		 $("#total").html(totalPrice); 
  } 
</script>
<script type="text/javascript">  
  
   function loadPay(){
	   var tp=parseInt(totalPrice)*100;alert(tp);
		$.ajax({
            url:"${ctx}/portal/pubv3pay/payExecute.html?random=" + Math.random(),            //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>  
			data: 'url='+window.location.href+'&totalPrice='+1,
	  		type:'post',
	  		dataType:'text',
	  		async:false,
	  		success:function(data){
    	  		if(data==""){
        	  		alert("与服务端交互异常");
					return;
        	  		}
			   var obj = eval('(' + data + ')');  
	            if(parseInt(obj.agent)<5){  
	                alert("您的微信版本低于5.0无法使用微信支付");  
	                return;  
	            }  
	            var randCode=obj.randCode;
	            wx.config({
	                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	                appId: obj.appId, // 必填，公众号的唯一标识
	                timestamp:obj.timeStamp , // 必填，生成签名的时间戳
	                nonceStr: obj.nonceStr, // 必填，生成签名的随机串
	                signature: obj.signature,// 必填，签名，见附录1
	                jsApiList: ['chooseWXPay'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	            });
	            wx.ready(function(){
	                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	                wx.chooseWXPay({
	                    timestamp: obj.timeStamp, // 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
	                    nonceStr: obj.nonceStr, // 支付签名随机串，不长于 32 位
	                    package: obj.packageValue, // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
	                    signType: obj.signType, // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
	                    paySign: obj.paySign, // 支付签名
	                    success: function (res) {
		                	alert(res.errMsg);
		                        // 支付成功后的回调函数
		                        location.href=obj.sendUrl+"&randCode="+randCode+"&out_trade_no="+obj.out_trade_no;
		                    }, cancel: function (res) {
		                        alert('已取消');
		                    },
		                    fail: function (res) {
		                      alert(JSON.stringify(res));
		                    }
	                });
	            });
	            wx.error(function(res){
	            	alert(res.errMsg);  
	                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

	            });
	            <%-- WeixinJSBridge.invoke('getBrandWCPayRequest',{  
	                "appId" : obj.appId,                  //公众号名称，由商户传入  
	                "timeStamp":obj.timeStamp,          //时间戳，自 1970 年以来的秒数  
	                "nonceStr" : obj.nonceStr,         //随机串  
	                "package" : obj.packageValue,      //<span style="font-family:微软雅黑;">商品包信息</span>  
	                "signType" : obj.signType,        //微信签名方式:  
	                "paySign" : obj.paySign           //微信签名  
	                },function(res){      
	                    alert(res.err_msg);  
	                if(res.err_msg == "get_brand_wcpay_request:ok" ) {  
	                    window.location.href=obj.sendUrl;  
	                }else{  
	                    alert("fail");  
	                    window.location.href="http://www.baidu.com";     
	                                       //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>  
	                }  
	            });  --%>
	  		},
	  		error:function(){
	  			 window.location.href="http://www.baidu.com";     
	  		} 
	  		
	  	});
	   }
  
    </script>  
</head>

<body>
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back"><span></span></div>
 <div class="header-title">确认订单</div>
 </div>
</header>
<div class="viewport"><%--
  <div class="step_order">
   <div class="addr_l">运至</div>
   <div class="addr_r">
     <a href="#" id="addAddress" class="s-href relative">
        <div class="mt_new">  
        <div class="s1-name">${adds.lxrxm}</div>
         <div class="s1-phone">${adds.lxrdh}</div>
        </div>
       <div class="addr">${adds.provno}省&nbsp;${adds.cityno}市&nbsp;${adds.xxdz}</div>
       <span class="s-point" ></span>
      </a>
      
      </div>
  </div>
  --%><div class="order_box">
  <div class="order_info">
   <h1 class="h1">订单信息</h1>
   <div class="edit_cart"><a href="${ctx}/admin/cars/listCars.html">编辑购物车</a></div>
  </div>
   <c:if test="${listcars !=null}">
   <c:forEach items="${listcars}" var="mb" varStatus="sta">
  <div class="cart_items">
   <div class="img-cart"><img src="${mb.cdpicturech}" width="60" height="60"></div>
   <div class="nr relative">
    <div class="title"><a href="#">${mb.cdname}</a></div>
    <div class="m_m">
    <span>￥</span><span class="money">${mb.cdprice}</span>
    <span>数量：</span><span class="min_text_box" >${mb.pnum}</span>
    </div>
   </div>
  </div>
  </c:forEach>
  </c:if>
  <c:if test="${listcars ==null}">
    <div class="viewport">
   <div class="cart_no">
   <span>您的购物车里还没有商品</span>
   <div class="go_cart"><a href="${ctx}/admin">现在去逛逛</a></div>
   </div>
</div>
  </c:if>
  </div>
  
</div>
<c:if test="${listcars !=null}">
<div class="bottom-fxied">
 <div class="payment-total-bar">
  <div class="ottom-total-price">总额：<font color="#FF0000">¥:<label id="total"></label></font></div>
  <span><a data-href="#" onclick="loadPay()">去结算（1）</a></span></div></div>
  </c:if>
</body>
</html>
