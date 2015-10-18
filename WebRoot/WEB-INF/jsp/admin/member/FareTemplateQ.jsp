<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/WEB-INF/tag.tld" prefix="p" %>
<%@taglib uri="permission-tags" prefix="pm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>userList</title>
<link href="${ctx}/theme/admin/default/css/master.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/theme/admin/default/css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx}/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script>


<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>
<script type="text/javascript">
	function delConfirm(id){
		$.layer({
		  shade : [0], //不显示遮罩
		  area : ['auto','auto'],
		  dialog : {
		      msg:'你确定要删除该订单吗?',
		      btns : 2,
		      type : 4,
		      btn : ['是','否'],
		      yes : function(){
		          location.href='${ctx}/admin/order/delete/' + id + '.html';
		      },
		      no : function(index){
		         layer.close(index);
		      }
		  }
		});
	}

  function find(){    
	$("#search_form").attr("action","${ctx}/admin/order/list/1.html");
	document.getElementById("search_form").submit();
    }   
    
   
</script>
</head>

<body>
<div class="admin_table">
<div class="content_box">

   <div class="list_info">
  	<form id="search_form" action="${ctx}/admin/cdInformation/list/1.html" method="post">
  	 <h2>订单查询</h2>
  	 <div class="div_input">
  	 <em>订单号：</em><input type="text" id="orderId" name="orderId" value="${params.orderId}"  class="input_a1" size="17"/>
  	 <em>支付类型：</em><select name="payType" id="payType"><option value="">--所有--</option><option value="0" <c:if test="${params.payType=='0'}">selected="selected"</c:if>>微信支付</option><option value="1" <c:if test="${params.payType=='1'}">selected="selected"</c:if>>货到付款</option></select>
  	  <em>支付状态：</em><select name="tag" id="tag"><option value="">--所有--</option><option value="0" <c:if test="${params.tag=='0'}">selected="selected"</c:if>>待支付</option><option value="1" <c:if test="${params.tag=='1'}">selected="selected"</c:if>>已支付</option><option value="2" <c:if test="${params.tag=='2'}">selected="selected"</c:if>>已取消</option></select>
  	  
  	  	<em>生成时间：</em>
			<input name="beginTime" id="beginTime" type="text" class="input_a1" value="<fmt:formatDate value="${params.beginTime}" pattern="yyyy-MM-dd" />"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  size="11"/> 
			至 <input name="endTime" id="endTime" type="text" class="input_a1" value="<fmt:formatDate value="${params.endTime}" pattern="yyyy-MM-dd" />"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="11"/>
								<input id="add_bt" type="button" value="查询" class="initial" onclick="find();"/>
  	</div>
  	 </form>
   
 
  <br/>
  <table width="98%" border="0" cellpadding="0" cellspacing="1">
	  <thead>
	  	<tr>
	  	     <th>模板名称</th>
	  	     <th>订单号</th> 
	  	      <th>总价格</th> 
	  		 <th>用户名</th> 
	  		 <th>生成时间</th> 
	  		 <th>支付时间</th> 
	  		 <th>支付类型</th> 
	  		 <th>支付状态</th>
	  		 
	        <th width="8%">操作</th>
	  	</tr>
	  </thead>
	  <tbody>
	  <!-- 变量 -->
	  
      <c:forEach items="${list}" var="o" varStatus="sta">
	      <tr >
	        <td>${sta.index + 1}</td>
	      	<td>${o.OrderId}</td>
	      	<td>${o.Price}</td>
	      	<td>${o.RealName}</td>
	      	<td><fmt:formatDate value="${o.PreTime}"
								pattern="yyyy-MM-dd" /></td>
	      	<td><fmt:formatDate value="${o.AfterTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
	      	<td>
	      	<c:choose>
	      	<c:when test="${o.PayType=='0'}">微信支付</c:when><c:when test="${o.PayType=='1'}">货到付款</c:when>
	      	</c:choose>
	      	</td>
	      	<td><c:choose>
	      	<c:when test="${o.Tag=='0'}">待支付</c:when><c:when test="${o.Tag=='1'}">已支付</c:when><c:when test="${o.Tag=='2'}">已取消</c:when>
	      	</c:choose>
	      	</td>
	        <td>
	         <div class="btn_icon">
		          	<input type="image" src="${ctx}/theme/default/images/file_icon.png" title="详细" onclick="javascript:location.href='${ctx}/admin/order/detail/${o.ID }.html'"/>
		          	 &nbsp;&nbsp;<input type="image" src="${ctx}/theme/default/images/del_icon.png" title="删除" onclick="delConfirm('${o.ID}')"/>
		         	</div>
	         </td>
	      </tr>
      </c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="9">
				<div class="page">
					<p:pager/>
				</div>
			</td>
		</tr>
	</tfoot>
    </table>
  </div>
</div>
</div>
</body>
</html>
