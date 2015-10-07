<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<title>订单列表</title>
<link href="${ctx }/about/style/master.css" rel="stylesheet" type="text/css">

</head>
<script type="text/javascript">
function formSubmit(){
	document.getElementById("search_form").submit();
}
</script>

<body>
<header>
<div class="logo"></div>
</header>
<div class="container">
<form action="${ctx }/portal/order/list/1.html" method="post" id="search_form">
条件查询:已支付 <input name="tag" type="radio" <c:if test="${tag==1 }">checked="checked"</c:if> value="1" onclick="formSubmit()"/>  &nbsp;&nbsp;&nbsp;&nbsp;未支付<input name="tag" type="radio" value="0"  <c:if test="${tag==0 }">checked="checked"</c:if> onclick="formSubmit()"/> <br/>
<table><tr><td>支付状态</td><td>收件人</td><td>地址</td><td>电话</td><td>总额(元)</td><td>操作</td></tr>
  <c:forEach items="${list}" var="o" varStatus="sta">
  <tr><td><a href="${ctx }/portal/order/detail.html?orderId=${o.OrderId}"><c:if test="${o.Tag==0}">待支付</c:if><c:if test="${o.Tag==1}">已支付</c:if></a></td>
  <td>${o.RealName}</td><td>${o.Address}</td><td>${o.Tel}</td><td>${o.Price/100}</td>

  <td><c:if test="${o.Tag==0}">&nbsp;&nbsp;<a href="${ctx }/portal/order/delete/${o.ID}.html">删除</a></c:if></td>
   </tr>
  </c:forEach>
  </table>
  </form>
</div>
</body>
</html>
