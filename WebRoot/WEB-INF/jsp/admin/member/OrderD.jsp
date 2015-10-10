<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="permission-tags" prefix="pm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>content</title>
<link href="${ctx}/adminthemes/css/style.css" type="text/css"
			rel="stylesheet" />
		<link href="${ctx}/adminthemes/default/css/master.css"
			rel="stylesheet" type="text/css" />
		<link href="${ctx}/adminthemes/default/css/default.css"
			rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>




<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>

</head>

<body>

<div class="admin_table">
<form id="add_form"  method="post">

  <div class="add_info">

   <h2>订单详细</h2><br/>
    <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
    <tr>
     <th class="w100">订单号:</th>
     <td>
     	${model.OrderId}
     </td>
   
          <th class="w100">支付方式:</th>
     <td>
     		<c:choose>
	      	<c:when test="${model.PayType=='0'}">微信支付</c:when><c:when test="${model.PayType=='1'}">货到付款</c:when>
	      	</c:choose>
     </td>
      <th class="w100">支付状态:</th>
     <td>
     		<c:choose>
	      	<c:when test="${model.Tag=='0'}">待支付</c:when><c:when test="${model.PayType=='1'}">已支付</c:when><c:when test="${model.PayType=='2'}">已取消</c:when>
	      	</c:choose></td>
    
         </tr>
            <tr>
     <th class="w100">总价格(元):</th>
     <td>
     	${model.Price/100}
     </td>
      <th class="w100">生成时间:</th>
     <td>
     		<fmt:formatDate value="${model.PreTime}"
								pattern="yyyy-MM-dd" /></td>
      <th class="w100">支付时间:</th>
     <td>
     		<fmt:formatDate value="${model.AfterTime}"
								pattern="yyyy-MM-dd" /></td>
         </tr>
              <tr>
                 <th class="w100">用户名:</th>
     <td>
     	${model.RealName}
     </td>
  <th class="w100">联系电话:</th>
     <td colspan="3">
     	 ${model.Tel}
     </td>
    
         </tr>
         <tr><th class="w100">地址:</th><td colspan="5">${model.Address }</td></tr>
   </table>
     
   
       <div class="div_submit">
					
					<input type="reset" value="返回" onclick="javascript:location.href='${ctx}/admin/order/list/1.html'"
						class="photo_btn" />
				</div>
  
  </div>
  </form>
</div>

</body>
</html>