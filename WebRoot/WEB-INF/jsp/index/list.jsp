<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>芸祥文具</title>
<link href="${ctx}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${ctx}/theme/style/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>

<script>
		var page = 1;
		var pid = '${pid}';
		$(function(){
	    list();
        })
        function list(){
	$.ajax({
				url:'${ctx}/admin/cdInformation/cdIndex.html?p='+page+'&id='+pid+'&random='+Math.random(),
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
	     	    $("#productsList").append("<li class='love-item'><a href='${ctx}/admin/cdInformation/detail.html?id="+list[i].id+"' class='link_ping'><span><img src='"+list[i].cdpicturech+"'></span><span class='love-item-title'>"+list[i].cdname+"</span></a><span class='love-item-price'>￥"+list[i].cdprice+"</span></li>");
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
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back"><span onclick="javascript:history.back(-1);"></span></div>
 <div class="header-title">产品列表</div>
 </div>
</header>
<div class="viewport">
<div class="list_order_info"><a href="#">${pcname}</a> </div>
  <div class="list_order_box">
  <ul class="love-list2" id="productsList">
    
  </ul>
   <div class="love-loading"><a href="javascript:void(0)" id="nextPage" onclick="list();return false;">点击继续加载</a></div>
  </div>
  
</div>
<div class="bottom-fxied"><footer><a data-href="#"><span class="home"></span><p>首页</p></a><a data-href="#"><span class="classify"></span><p>分类</p></a><a data-href="#"><span class="cart"></span><p>购物车</p></a><a data-href="#"><span class="my"></span><p>会员中心</p></a></footer></div>
</body>
</html>
