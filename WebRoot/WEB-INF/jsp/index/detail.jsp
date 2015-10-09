<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>芸祥文具</title>
<link href="${pageContext.request.contextPath}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/theme/style/master.css" rel="stylesheet" type="text/css">
<script src='${pageContext.request.contextPath}/theme/js/hhSwipe.js' type="text/javascript"></script>
<script src='${pageContext.request.contextPath}/theme/js/jquery.min.js' type="text/javascript"></script>
 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/index_files/animate.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/theme/index_files/flavr.css"> 
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/jquery.browser.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/livedemo.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/highlight.pack.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>

<!-- END DEMO PAGE SCRIPT -->
<!-- BEGIN flavr SCRIPT -->

<script type="text/javascript" src="${pageContext.request.contextPath}/theme/index_files/flavr.min.js"></script>
<script>
$(document).ready(function(){
//获得文本框对象
   var t = $("#text_box");
//初始化数量为1,并失效减
$('#min').attr('disabled',true);
    //数量增加操作
    $("#add").click(function(){    
        t.val(parseInt(t.val())+1)
        if (parseInt(t.val())!=1){
            $('#min').attr('disabled',false);
        }
      
    }) 
    //数量减少操作
    $("#min").click(function(){
        t.val(parseInt(t.val())-1);
        if (parseInt(t.val())==1){
            $('#min').attr('disabled',true);
        }
      
    })
   
   
    $('#a1').on('click', function(){
               var pid=$('#pid').val();
               var num=$('#text_box').val();
               $.ajax({
				url:'${ctx}/admin/cars/toCars.html?id='+pid+'&num='+num+'&random='+Math.random(),
		  		type:'post',
		  		dataType:'json',
		  		async:false,
		  		success:function(data){
		  		if(data.exl=="ok"){
		  		 new $.flavr({ content : '产品已添加到购物车',
                            buttons : { success : { text: '继续购物', style: 'success', action:
                            function(){ this.exit(); } }, 
                            danger : { text: '立即结算', style: 'danger',
                            action: function(){ listcars(); } } } });
		  		}
		  		},
		  		error:function(){
		  		} 
		  	  });
               
               
               

            });
});

$(function(){
	        	$("#buyNow").attr("href","${ctx}/portal/order/orderSure.html");
	        	$("#car").attr("href","${ctx}/portal/order/listCars.html");
});


function listcars(){
 document.forms[0].action="${ctx}/portal/order/listCars.html";
	  document.forms[0].submit();
	  }
	  
	  

</script>
</head>

<body>
<form id="add_form" method="post">
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back" ><span onclick="javascript:history.back(-1);"></span></div>
 <div class="header-title">商品详情</div>
 </div>
</header>
<div class="viewport">
  <div class="flash">
    <div class="addWrap">
      <div class="swipe" id="mySwipe">
        <div class="swipe-wrap">
        <c:forEach items="${Cdpicturech}" var="mb" varStatus="sta"> 
	       <div><a href="javascript:;"><img class="img-responsive" src="${mb['cdpicture']}"/></a></div>
            </c:forEach>
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
 <h1 id="title" class="detail-title"><a href="/detail/1145227218.html?sid=f24396d6c8b32b0d684154417d3b3927" class="dis-blk J_ping" report-eventid="MProductdetail_Productinfo" report-pageparam="1145227218" id="wareName">${cf.cdname} </a></h1>
 <div class="detail-price"><span class="p-price">价格：¥${cf.cdprice}</span></div>
  </div>
  <div class="quantity">
  <div class="sl">数量：</div>
  <div class="member">
  <input type="hidden" name="pid" id="pid" value="${cf.id}"/>
  <input id="min" name="" type="button" value="" class="input" />  
  <input id="text_box" name="" type="text" value="1" class="input2" />  
  <input id="add" name="" type="button" value=""  class="input3" /></div>
  </div>
  <div class="detaile">
   <h1>商品详情</h1>
   <div class="text">
    ${cf.cdintroduction}
   </div> 
  </div>
</div>
<div class="bottom-fxied"><div class="de_cart" ><span id="a1"><a data-href="#" class="a1" >加入购物车</a></span><span id="a1"><a data-href="#" id="car" class="a2" >购物车</a></span></div></div>
</form>
</body>

</html>
