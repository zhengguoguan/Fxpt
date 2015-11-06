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

   <script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/theme/js/city.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.cityselect.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script>


<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>
<script type="text/javascript">
$(function(){
	$("#city_3").citySelect({
	    	prov:"广东", 
	    	city:"广州"
		});
	});
	function delConfirm(id){
		$.layer({
		  shade : [0], //不显示遮罩
		  area : ['auto','auto'],
		  dialog : {
		      msg:'你确定要删除该模版吗?',
		      btns : 2,
		      type : 4,
		      btn : ['是','否'],
		      yes : function(){
		          location.href='${ctx}/admin/fareTemplate/delete/' + id + '.html';
		      },
		      no : function(index){
		         layer.close(index);
		      }
		  }
		});
	}

  function find(){    
	$("#search_form").attr("action","${ctx}/admin/fareTemplate/list/1.html");
	document.getElementById("search_form").submit();
    }   
    
   
</script>
</head>

<body>
<div class="admin_table">
<div class="content_box">
  <div class="btn_box">
  		<input id="add_bt" type="button" value="添加" class="initial" style="cursor:hand" onclick="javascript:location.href='${ctx}/admin/fareTemplate/add/new.html'"/>
       
  </div>
   <div class="list_info">
  	<form id="search_form" action="${ctx}/admin/fareTemplate/list/1.html" method="post">
  	 <h2>模版查询</h2>
  	 <div class="div_input" id="city_3">
  	 <em>模版名称：</em><input type="text" id="name" name="name" value="${params.name}"  class="input_a1" size="17"/>
  	 <em>宝贝地址：</em>
  <select class="prov" name="provno"  id="provno"></select>省<select class="city" name="cityno"  id="cityno" disabled="disabled"></select>市
 
								<input id="add_bt" type="button" value="查询" class="initial" onclick="find();"/>
  	</div>
  	 </form>
   
 
  <br/>
  <table width="98%" border="0" cellpadding="0" cellspacing="1">
	  <thead>
	  	<tr>
	  	<th></th>
	  	     <th>模板名称</th>
	  	     <th>宝贝地址</th> 
	  	      <th>发货时间</th> 
	  		 <th>是否包邮</th> 
	  		 <th>计价方式</th> 
	  		
	  		 
	        <th width="8%">操作</th>
	  	</tr>
	  </thead>
	  <tbody>
	  <!-- 变量 -->
	  
      <c:forEach items="${list}" var="o" varStatus="sta">
	      <tr >
	        <td>${sta.index + 1}</td>
	      	<td>${o.Name}</td>
	      	<td>${o.ShopAddr}</td>
	      	<td>
	      	 	<c:choose>
	      	<c:when test="${o.DispatchTime=='12'}">12小时内</c:when>
	      	<c:when test="${o.DispatchTime=='24'}">24小时内</c:when>
	      		<c:when test="${o.DispatchTime=='48'}">48小时内</c:when>
	      			<c:when test="${o.DispatchTime=='72'}">72小时内</c:when>
	      				<c:when test="${o.DispatchTime=='120'}">5天内</c:when>
	      				<c:when test="${o.DispatchTime=='168'}">7天内</c:when>
	      				<c:when test="${o.DispatchTime=='360'}">15天内</c:when>
	      				<c:when test="${o.DispatchTime=='630'}">30天内</c:when>
	      				<c:when test="${o.DispatchTime=='960'}">40天内</c:when>
	      	</c:choose>
	      	</td>
	      
	      	<td>
	      	<c:choose>
	      	<c:when test="${o.IsFreePostage=='1'}">包邮</c:when>
	      	<c:when test="${o.IsFreePostage=='2'}">不包邮</c:when>
	      	</c:choose>
	      	</td>
	      	<td><c:choose>
	      	<c:when test="${o.ValuationModel=='1'}">按件</c:when>
	      	<c:when test="${o.ValuationModel=='2'}">按重量</c:when>
	      	<c:when test="${o.ValuationModel=='3'}">按体积</c:when>
	      	</c:choose>
	      	</td>
	        <td>
	         <div class="btn_icon">
		          	<input type="image" src="${ctx}/theme/default/images/file_icon.png" title="详细" onclick="javascript:location.href='${ctx}/admin/fareTemplate/detail/${o.ID }.html'"/>
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
