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
<script type="text/javascript" src="${ctx}/js/jsp/base/default_tr.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery_autocomplete/jquery.autocomplete.js"></script>
<link href="${ctx}/js/jquery_autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>
<script type="text/javascript">

$(document).ready(function(){
$("#checkAll").click(function(){
	$("input[name=qyId]").attr("checked",this.checked);
});
});
	function delConfirm(id){
		$.layer({
		  shade : [0], //不显示遮罩
		  area : ['auto','auto'],
		  dialog : {
		      msg:'是否要删除：' + id,
		      btns : 2,
		      type : 4,
		      btn : ['是','否'],
		      yes : function(){
		          location.href='${ctx}/admin/mbMember/delete/' + id + '.html';
		      },
		      no : function(index){
		         layer.close(index);
		      }
		  }
		});
	}
   function find(){    
	$("#search_form").attr("action","${ctx}/admin/mbMember/list/1.html");
	document.getElementById("search_form").submit();
    }   

function zyjfx(type){
	var stuInput = $("input[name=qyId]:checked");
	var ids = '';
	var url='';
	$.each(stuInput,function(i,item){
		ids += item.value + ",";
	});
	if(ids == ''){
		alert('请选择要转一级分销的用户。');
		return;
	}
	if(type =='1'){
	url='${ctx}/admin/mbMember/Zyjfx.html?ids=';
	}else if(type =='2'){
	url='${ctx}/admin/mbMember/Zejfx.html?ids=';
	}else{
	url='${ctx}/admin/mbMember/Zsjfx.html?ids=';
	}
	alert(url);
	$.ajax({
				url:url+ids+'&random='+Math.random(),
		  		type:'post',
		  		dataType:'json',
		  		async:false
		  		
		  	});
	$("#search_form").attr("action","${ctx}/admin/mbMember/list/"+${page.currentPage}+".html");
	document.getElementById("search_form").submit();
}
</script>
</head>

<body>
<div class="admin_table">
<div class="content_box">
  <div class="btn_box">

  		<input id="add_bt" type="button" value="添加" class="initial" onclick="javascript:location.href='${ctx}/admin/mbMember/add/new.html'"/>
     
  </div>
   <div class="list_info">
  	<form id="search_form" action="${ctx}/admin/mbMember/list/1.html" method="post">
  	<h2>分销级别设置</h2>
  	<div class="div_input">
  	 <em>会员名：</em><input type="text" id="fmbname" name="fmbname" value="${fmbname}"  class="input_a1" size="20"/>
  	 <em>微信名：</em><input type="text" id="fwxname" name="fwxname" value="${fwxname}"  class="input_a1" size="20"/>
  	  <input id="add_bt" type="button" value="查询" class="initial" onclick="find();"/>
  	   <input id="add_bt" type="button" value="转一级分销" class="initial" onclick="zyjfx(1)"/>
  	    <input id="add_bt" type="button" value="转二级分销" class="initial" onclick="zyjfx(2)"/>
  	     <input id="add_bt" type="button" value="转三级分销" class="initial" onclick="zyjfx(3)"/>
  	     
  	</div>
  	  </form>
    
 
  <br/>
  <table width="98%" border="0" cellpadding="0" cellspacing="1">
	  <thead>
	  	<tr>
	  	     <th><input type="checkbox" id="checkAll"/>全选</th>
	  	     <th>序号</th>
	  		 <th>会员名</th> 
	  		 <th>微信名</th> 
	  		 <th>微信Id</th> 
	  		 <th>分销级别</th> 
	        <th width="13%">操作</th>
	  	</tr>
	  </thead>
	  <tbody>
	  <!-- 变量 -->
	  <pm:hasPermission permValue="mb_save">
	       	<c:set var="mb_save" value="true"/>
	    </pm:hasPermission>
	    <pm:hasPermission permValue="mb_updt">
	       	<c:set var="mb_updt" value="true"/>
	    </pm:hasPermission>
	    <pm:hasPermission permValue="mb_del">
	       	<c:set var="mb_del" value="true"/>
	    </pm:hasPermission>
      <c:forEach items="${list}" var="mb" varStatus="sta">
	      <tr ondblclick="javascript:location.href='${ctx}/admin/mbMember/add/new.html?id=${mb.id}'">
	         <td><input type="checkbox" value="${mb.id}" name="qyId"/></td>
	        <td>${sta.index + 1}</td>
	      	<td>${mb.mbname}</td>  
	      	<td>${mb.wxname}</td>
	      	<td>${mb.openid}</td>
	      	<td>${mb.mbtype}</td>
	      	
	        <td>
	             <input id="add_bt" type="button" value="生成名片" class="initial" onclick="javascript:location.href='${ctx}/admin/mbMember/Schct.html?id=${mb.id}'"/>
	          	<c:if test="${mb_updt == true}">
		          	<div class="btn_icon">
		          	 <input type="image" src="${ctx}/theme/default/images/edit_icon.png" title="修改" onclick="javascript:location.href='${ctx}/admin/mbMember/add/new.html?id=${mb.id}'"/>
		          	</div>
	          	</c:if>
	          	<c:if test="${mb_del == true}">
		          	<div class="btn_icon">
		          	 <input type="image" src="${ctx}/theme/default/images/del_icon.png" title="删除" onclick="delConfirm('${mb.id}')"/>
		         	</div>
	         	</c:if>
	         </td>
	      </tr>
      </c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="7">
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
