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
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/common.js"></script>

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
//初始化数量为1,并失效减
      $("input[name='check_item']").attr("checked","true");
       $("input[name='check_item'],#check_all,#box_all").toggleClass("checked");
      setTotal(); 
    //数量增加操作
    $(".add").click(function(){ 
    var t=$(this).parent().find('input[class*=min_text_box]'); 
    t.val(parseInt(t.val())+1) ;
	setTotal(); 
  }) 
    //数量减少操作
   $(".min").click(function(){ 
    var t=$(this).parent().find('input[class*=min_text_box]'); 
    t.val(parseInt(t.val())-1) 
    if(parseInt(t.val())<0){ 
      t.val(0); 
    } 
	setTotal(); 
  }) 
   
   $('#toOrder').on('click', function(){
               var listsize="${listsize}";
               if(listsize>0){
               document.forms[0].action="${ctx}/portal/order/changeCars.html";
               }else{
               document.forms[0].action="${ctx}/portal/order/listCars.html";
               }
               document.forms[0].submit();
              });
  
});
function setTotal(){ 
    var s=0; 
    $(".cart_items").each(function(){ 
		var ifon=$(this).find('input[class*=checkbox]:checked').val();
		if(ifon =='on'){
		 s+=parseInt($(this).find('input[class*=min_text_box]').val())
             *parseFloat($(this).find('span[class*=shp-cart-item-price]').text()); 
        
        }
		 }); 
		 $("#total").html(s.toFixed(2)); 
  } 

function delConfirm(id){
		
		
		new $.flavr({ content : '是否要删除该商品',
                            buttons : { success : { text: '是', style: 'success', action:
                            function(){   location.href='${ctx}/portal/order/removeCars.html?id=' + id; } }, 
                            danger : { text: '否', style: 'danger',
                            action: function(){ this.exit(); } } } });
	}
	
	function toPayFor(payTypeValue){
	$("#payType").val(payTypeValue);
	document.forms[0].action="${ctx}/portal/order/changeCars.html";
	document.forms[0].submit();
}
	function back(){
      document.forms[0].action="${ctx}/index.jsp";
	   document.forms[0].submit();
	   }
</script>
</head>

<body>
<form id="add_form" method="post">
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back"><span onclick="javascript:back();"></span></div>
 <div class="header-title">购物车</div>
 </div>
</header>
<c:if test="${listsize >0}">
<div class="viewport">

   <c:forEach items="${listcars}" var="mb" varStatus="sta">
  <div class="cart_items">
  <div class="cart-checkbox"><input type="checkbox" class="checkbox"  name="check_item" onchange="setTotal()"></div>
   <div class="img-cart"><img src="${mb.cdpicturech}" width="60" height="60"></div>
   <div class="nr relative">
    <div class="title"><a href="#">${mb.cdname}</a></div>
    <div class="member">
  
  <input type="hidden" id="id" name="id" value="${mb.id}"/>
  <input id="min" name="" type="button" value="" class="min" />  
  <input id="text_box" name="num" type="text" value="${mb.pnum}" class="min_text_box"  />  
  <input id="add" name="" type="button" value="" class="add" /></div>
  <div class="cart-product-memny"><span>¥</span><span class="shp-cart-item-price" id="price1575898327">${mb.cdprice}</span></div>
  <div class="wastebin-container">
     <div class="wastebin" onclick="delConfirm('${mb.id}')">
          <i class="wastebin-up"></i>
          <i class="wastebin-down"></i>
     </div>
   </div>
   
   </div>
  </div>
  </c:forEach>
  </div>
 </c:if>
 <c:if test="${listsize <=0}">
    <div class="viewport">
   <div class="cart_no">
   <span>您的购物车里还没有商品</span>
   <div class="go_cart"><a href="${ctx}/index.jsp">现在去逛逛</a></div>
   </div>
  </div>
  </c:if>
<div class="bottom-fxied">
 <div class="payment-total-bar">
  <div class="ottom-total-price">总额：<font color="#FF0000"><label id="total"></label></font></div>
  <span id="toOrder"><a data-href="#" >去结算（1）</a></span></div></div>
  </form>
</body>


</html>
