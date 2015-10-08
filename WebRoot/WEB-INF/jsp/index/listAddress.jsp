<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>芸祥文具</title>
<link href="${pageContext.request.contextPath}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/theme/style/master.css" rel="stylesheet" type="text/css">
<script src='${pageContext.request.contextPath}/theme/js/jquery.min.js' type="text/javascript"></script>




<script type="text/javascript">
$(function(){
	//单独选择某一个
	
	
	$("input[name='check']").click(function(){
	         $("input[name='check']").attr("class","checkbox");;
	         $("input[name='checkvalue']").attr("value","0");
			var index=$("input[name='check']").index(this);
			$("input[name='check']").eq(index).toggleClass("checked");//伪复选
			$("input[name='checkvalue']").eq(index).attr("value","1");//伪复选
			document.forms[0].action="${ctx}/admin/address/changeAddress.html";
            document.forms[0].submit();  
	});	
	//全选
	

});
 function back(){
 document.forms[0].action="${ctx}/portal/order/changeCars.html";
	   document.forms[0].submit();
	   }
</script>
</head>

<body>
<form id="add_form" method="post">
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back"><span onclick="javascript:back();"></span></div>
 <div class="header-title">选择联系地址</div>
 </div>
</header>
<div class="viewport">
  <div class="add_n_addr">
   <a href="${ctx}/admin/address/addAddress.html" ><div class="add_box_addr relative">添加新地址<span></span></div></a>
  </div>
  <div class="addr_box"> 
    <c:forEach items="${listaddress}" var="mb" varStatus="sta">
   <div class="addr_box_k">
  <div class="cart-checkbox"><input type="hidden" id="id" name="id" value="${mb.id}"/><input type="hidden" name="checkvalue" id="checkvalue" <c:if test="${mb.seltype==1}">value="1"</c:if>/><input type="checkbox"  name="check"  value="0"  <c:if test="${mb.seltype==1}">class="checked"</c:if>  <c:if test="${mb.seltype!=1}">class="checkbox"</c:if> id="check"></div>
     <div class="addr_r">
     <a href="#" class="s-href relative"> 
        <div class="mt_new">
        <div class="s1-name">${mb.lxrxm}</div> 
         <div class="s1-phone">${mb.lxrdh}</div> 
        </div> 
       <div class="addr">${mb.xxdz}</div>    
      </a>
      
      </div>
      </div>
      </c:forEach>
      
    
     
     </div>
  
</div>
<div class="bottom-fxied"><footer><a data-href="#"><span class="home"></span><p>首页</p></a><a data-href="#"><span class="classify"></span><p>分类</p></a><a data-href="#"><span class="cart"></span><p>购物车</p></a><a data-href="#"><span class="my"></span><p>会员中心</p></a></footer></div>
</form>
</body>
</html>
