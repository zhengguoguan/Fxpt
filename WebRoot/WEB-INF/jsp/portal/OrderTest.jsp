<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>付款成功</title>
<link href="${ctx }/about/style/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" charset="UTF-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

</head>

<body>  
    <form action="" method="post" >  
        <input type="button" value="确认支付" name="ajaxLoadId" id="test" onclick="loadPay()"/>  
    </form>  
    <script type="text/javascript">  
  
   function loadPay(){
		$.ajax({
            url:"${ctx}/portal/pubv3pay/payExecuteTest.html?totalPrice=1&url="+window.location.href,            //<span style="font-family:微软雅黑;">ajax调用微信统一接口获取prepayId</span>  
	  		type:'post',
	  		dataType:'text',
	  		async:false,
	  		success:function(data){
			   var obj = eval('(' + data + ')');  
	            if(parseInt(obj.agent)<5){  
	                alert("您的微信版本低于5.0无法使用微信支付");  
	                return;  
	            }  
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
		                        location.href="http://www.qq.com";
		                    }, cancel: function (res) {
		                        alert('已取消');
		                    },
		                    fail: function (res) {
		                      alert(JSON.stringify(res));
		                    }
	                });
	                wx.error(function(res){
		            	alert(res.errMsg);  
		                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

		            });
	            });
	         
	          
	  		},
	  		error:function(){
	  			 window.location.href="http://www.baidu.com";     
	  		} 
	  		
	  	});
	   }
  
    </script>  
  </body>  
</html>
