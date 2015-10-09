<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>芸祥文具 </title>
<link href="${ctx}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${ctx}/theme/style/master.css" rel="stylesheet" type="text/css">
<script src='${ctx}/theme/js/hhSwipe.js' type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>

<script>
		var page = 1; 
</script>
<script>
$(function(){
	list();
	$('#cart').on('click', function(){
              document.forms[0].action="${ctx}/portal/order/listCars.html";
              document.forms[0].submit();
              });
   $('#home').on('click', function(){
              document.forms[0].action="${ctx}/index.jsp";
              document.forms[0].submit();
              });
})
function list(){
	$.ajax({
				url:'${ctx}/admin/cdInformation/cdIndex.html?p='+page+'&random='+Math.random(),
		  		type:'post',
		  		dataType:'json',
		  		async:false,
		  		success:function(data){
		  		if(data.status==1){
	        	var list = data.list;
	        	
	        	if(data.isNextPage==0){
	        		$("#nextPage").html("已全部加载完");
	        		$("#nextPage").attr("disabled","disabled");
	        	}else{
	        	for(var i=0;i<list.length;i++){
	     	    $("#productsList").append("<li class='love-item'><a href='admin/cdInformation/detail.html?id="+list[i].id+"' class='link_ping'><span><img src='"+list[i].cdpicturech+"'></span><span class='love-item-title'>"+list[i].cdname+"</span></a><span class='love-item-price'>￥"+list[i].cdprice+"</span></li>");
	        	}
	        	}
	        	page ++;
	        }else{
	        	alertDefaultStyle("mini", "已全部加载完");
	        }
		  		},
		  		error:function(){
		  		} 
		  		
		  	});
}
</script>	
</head>

<body>
<form id="add_form" method="post">
<div class="mhome_top"><img src="${ctx}/theme/images/logo.png" width="116" height="46"></div>
<div class="viewport">
  <div class="search relative">
    <input type="text" value="搜 索">
    <a href="javascript:void(0)" id="index_search_submit" class="icon-search"><span>搜索</span></a></div>
  <div class="flash">
    <div class="addWrap">
      <div class="swipe" id="mySwipe">
        <div class="swipe-wrap">
          <div><a href="javascript:;"><img class="img-responsive" src="${ctx}/theme/images/slider1-1.png"/></a></div>
          <div><a href="javascript:;"><img class="img-responsive" src="${ctx}/theme/images/slider1-2.png" /></a></div>
          <div><a href="javascript:;"><img class="img-responsive" src="${ctx}/theme/images/slider1-3.png"/></a></div>
        </div>
      </div>
      <ul id="position">
        <li class="cur"></li>
        <li></li>
        <li></li>
      </ul>
    </div>
    <script type="text/javascript">
var bullets = document.getElementById('position').getElementsByTagName('li');

var banner = Swipe(document.getElementById('mySwipe'), {
	auto: 4000,
	continuous: true,
	disableScroll:false,
	callback: function(pos) {
		var i = bullets.length;
		while (i--) {
			bullets[i].className = ' ';
		}
		bullets[pos].className = 'cur';
	}
})
</script> 
  </div>
  <div class="nav">
    <ul>
      <li><a href="#" class="n1">首页</a></li>
      <li><a href="#" class="n2">分类</a></li>
      <li><a href="#" class="n3">会员</a></li>
      <li><a href="#" class="n4">购物车</a></li>
    </ul>
  </div>
  <div class="hot_pr">
    <div class="hot_tj">
      <div class="title relative"><em></em>
        <h1>热销产品</h1>
      </div>
      <ul class="seckill-list">
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_jfs/t1504/312/806033064/289836/ddc8788a/55ac8e81N128568d8.jpg!q70.jpg" border="0" class="seckill-photo"> </a> <span class="seckill-price">¥61.80</span> <span class="seckill-discount">3.1折</span> </li>
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_g17/M00/00/0A/rBEbSFNpyqYIAAAAAAPWY8SAcVcAAAECQPWElgAA9Z7647.jpg!q70.jpg" border="0" class="seckill-photo"></a><span class="seckill-price">¥54.00</span> <span class="seckill-discount">7.9折</span> </li>
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_jfs/t1714/365/119122921/178587/a4fac56a/55cbff62Nab35e310.jpg!q70.jpg" border="0" class="seckill-photo"></a><span class="seckill-price">¥36.90</span> <span class="seckill-discount">9.2折</span> </li>
      </ul>
    </div>
  </div>
  <div class="hot_pr">
    <div class="hot_tj">
      <div class="title relative"><em></em>
        <h1>推荐产品</h1>
      </div>
      <ul class="seckill-list">
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_jfs/t1504/312/806033064/289836/ddc8788a/55ac8e81N128568d8.jpg!q70.jpg" border="0" class="seckill-photo"> </a> <span class="seckill-price">¥61.80</span> <a class="seckill-discount" href="#">点击购买</a> </li>
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_g17/M00/00/0A/rBEbSFNpyqYIAAAAAAPWY8SAcVcAAAECQPWElgAA9Z7647.jpg!q70.jpg" border="0" class="seckill-photo"></a><span class="seckill-price">¥54.00</span> <a class="seckill-discount" href="#">点击购买</a> </li>
        <li class="seckill-item"><a href="/seckill/seckillList" class="seckill-link J_ping"><img src="http://m.360buyimg.com/mobilecms/s220x220_jfs/t1714/365/119122921/178587/a4fac56a/55cbff62Nab35e310.jpg!q70.jpg" border="0" class="seckill-photo"></a><span class="seckill-price">¥36.90</span> <a class="seckill-discount" href="#">点击购买</a> </li>
      </ul>
    </div>
  </div>
  <div class="section">
    <div class="section_box">
        <h1>精选类目</h1>
      <ul class="category">
       <li><a href="admin/cdInformation/List.html?Pid=1"><img src="${ctx}/theme/images/a01.png" width="100%"></a></li>
        <li><a href="admin/cdInformation/List.html?Pid=2"><img src="${ctx}/theme/images/a02.png" width="100%"></a></li>
        <li><a href="admin/cdInformation/List.html?Pid=3"><img src="${ctx}/theme/images/a03.png" width="100%"></a></li>
        <li><a href="admin/cdInformation/List.html?Pid=4"><img src="${ctx}/theme/images/a04.png" width="100%"></a></li>
        <li><a href="admin/cdInformation/List.html?Pid=5"><img src="${ctx}/theme/images/a05.png" width="100%"></a></li>
        <li><a href="admin/cdInformation/List.html?Pid=6"><img src="${ctx}/theme/images/a06.png" width="100%"></a></li>
      </ul>
    </div>
  </div>`
  <div class="guess">
   <div class="title relative">
    <h1>猜你喜欢</h1>
   </div>
   <ul class="love-list"  id="productsList">
    
    
   </ul>
    <div class="love-loading"><a href="${ctx }/portal/order/list/1.html?tag=0"  >模拟查看历史订单</a></div>
    <div class="love-loading"><a href="${ctx }/portal/order/orderTest.html"  >模拟点击支付</a></div>
   <div class="love-loading"><a href="javascript:void(0)" id="nextPage" onclick="list();return false;">点击继续加载</a></div>
  </div>
</div>
<div class="bottom-fxied"><footer><a data-href="#"><span class="home" id="home"></span><p>首页</p></a><a data-href="#"><span class="classify"></span><p>分类</p></a><a data-href="#"><span class="cart" id="cart"></span><p>购物车</p></a><a data-href="#"><span class="my"></span><p>会员中心</p></a></footer></div>
</form>
</body>
</html>
