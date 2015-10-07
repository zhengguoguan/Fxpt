<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String id = request.getParameter("id");
%>

<!DOCTYPE html>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta content="telephone=no" name="format-detection">
    <meta name="wap-font-scale" content="no">
    <title id="title"></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jd/base.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jd/detail.css">
    <script type="text/javascript" async="" src="${pageContext.request.contextPath}/js/aywmq.js"></script>
    <script async="" src="${pageContext.request.contextPath}/js/analytics.js"></script>
    <script type="text/javascript" async="" src="${pageContext.request.contextPath}/js/da_opt.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/zepto.min.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script>
    //商品ID
    var id = "${id}";
        //数据埋点
    var snga = snga||{};
	//初始化基本属性
	var sn = sn||{
		
	};
$(function(){
	$("#propicture").attr("src",'${cf.cdpicturech}');
	        	$("#title").html('${cf.cdname}');
	        	$("#protitle").html('${cf.cdname}');
	        	$("#proprice").html("￥"+'${cf.cdprice}');
                $("#procontent").html('${cf.cdintroduction}');
	        	$("#buyNow").attr("href","${ctx}/admin/orders/toadd.html?pid="+'${cf.id}');
});
function dotran($str) {
        $str = str_replace('"','//"',$str);
        $str = str_replace("/r/n",'//r//n',$str);
        $str = str_replace("/t",'//t',$str);
        $str = str_replace("//",'//',$str);
        $str = str_replace("/b",'//b',$str);
        return $str;
    }
</script>
</head>
<body>

<section class="detail-content">
<div class="sn-nav sn-block">
    <div class="sn-nav-back"><a data-name="prodDetail_none_main_back" class="sn-iconbtn" href="javascript:history.go(-1);">返回</a></div>
    <div class="sn-nav-title of">商品详情</div>
</div>
<section>
<section class="pic">
    <div class="pic-slider lazyimg" style="visibility: visible;">
        <ul style="width: 3750px;">
						<li class="item" data-index="0" style="width: 750px; left: 0px; transition-duration: 0ms; -webkit-transition-duration: 0ms; -webkit-transform: translate(0px, 0px) translateZ(0px);">
						<img data-name="prodDetail_none_main_image" id="propicture">
						</li>
        </ul>
    </div>
  
</section>
<div class="intro cm-pd-white">
    <div class="intro-price"><div id="priceInfo">
        
            
				<span class="price-a" id="proprice"></span>
                
    		
        
    </div></div>
	<div class="intro-book"></div>
    <div class="intro-title">
        <div class="desc" data-event="to-grahic" id="protitle">
        </div>
    </div>

</div>


</section>

</section>
<section class="shopping-car" style="display: block;">
    <div>
        <div class="shopping-car-icon">
            <a data-name="prodDetail_none_main_shoppingCart" href="http://cart.m.suning.com/project/cart/cart1.html"><span style="display: none;"><em id="cartNum"></em></span></a>
        </div>
        <div class="shopping-car-fav">
            <a name="" id="favId" href="javascript:void(0);" data-name="prodDetail_none_main_collect"></a>
        </div>
        <div class="shopping-car-fun">
            <a data-name="prodDetail_none_main_buy" id="buyNow" href="javascript:void(0);" style="-webkit-transform-origin: 0px 0px 0px; opacity: 1; -webkit-transform: scale(1, 1);">立即购买</a>
        </div>
    </div>
</section>
<div class="detail-desc">

    <div class="sn-tab choose cm-v-gap">
        <ul class="sn-tab-nav wbox">
            <li data-name="prodDetail_none_main_prodSpec" class="current">详情</li>
        </ul>
        <div id="procontent">
     
        </div>
    </div>
</div>

<div class="copyright sn-block tc pdlayout sn-txt-muted">
				Copyright © 2015 -<script type="text/javascript">document.write(new Date().getFullYear())</script> ${config.siteName } 版权所有
			</div>

<script type="text/javascript" src="js/after-min.js"></script>


</body></html>