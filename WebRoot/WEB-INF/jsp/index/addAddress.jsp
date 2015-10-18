<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html style="width: 100%; height: 100%;">
<head>
<meta charset="utf-8">
<meta content="width=device-width, minimum-scale=1,initial-scale=1, maximum-scale=1, user-scalable=1;" id="viewport" name="viewport" />
<title>新增地址</title>
<link href="${pageContext.request.contextPath}/theme/style/index.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/theme/style/master.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/city.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery.cityselect.js"></script>


<script type="text/javascript">

$(function(){
$("#city_3").citySelect({
    	prov:"广东", 
    	city:"广州"
	});
	
	$("#save").click(function(){
	   $("#save").attr("onclick","");
        document.forms[0].action="${ctx}/admin/address/save.html";
	   document.forms[0].submit();
      
    })
	
});

</script>
</head>

<body>
<form id="add_form"   method="post">
<header class="home_header">
 <div class="header-bar"><div class="header-icon-back"><span onclick="javascript:history.back(-1);"></span></div>

 <div class="header-title">创建联系地址</div>
 </div>
</header>
<div class="viewport">
  <ul class="add_address">
   <li class="li2">联系人姓名</li>
   <li class="li"><input name="lxrxm"  id="lxrxm" type="text" class="input input_left" placeholder="请输入联系人姓名"></li>
   <li class="li2">联系人电话</li>
   <li class="li"><input name="lxrdh"  id="lxrdh" type="text" class="input input_left" placeholder="请输入联系人电话"></li>
   <li class="li2">所在地区</li>
   <div id="city_3">
   <li class="li"><select class="prov" name="provno"  id="provno"></select> </li>
   <li class="li2">省</li>
   <li class="li"><select class="city" name="cityno"  id="cityno" disabled="disabled"></select></li>
  	<li class="li2">市</li>	
    	
    </div>
   <li class="li2">详细地址</li>
   <li class="li"><input name="xxdz"  id="xxdz" type="text" class="input input_left" placeholder="详细地址门牌号"></li>
  </ul>
  <div class="add_btn"><a data-href="#" id="save">保存地址</a></div>
</div>
<div class="bottom-fxied"><footer><a data-href="#"><span class="home"></span><p>首页</p></a><a data-href="#"><span class="classify"></span><p>分类</p></a><a data-href="#"><span class="cart"></span><p>购物车</p></a><a data-href="#"><span class="my"></span><p>会员中心</p></a></footer></div>
</form>
</body>
</html>
