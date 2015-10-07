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
		          location.href='${ctx}/admin/cdInformation/delete/' + id + '.html';
		      },
		      no : function(index){
		         layer.close(index);
		      }
		  }
		});
	}

  function find(){    
	$("#search_form").attr("action","${ctx}/admin/cdInformation/list/1.html");
	document.getElementById("search_form").submit();
    }   
    
    function loadPageLayer(title,url){
	var mypop = $.layer({
	    type: 2,
	    title: title,
	    iframe: {src : url},
	    maxmin: true,
	    area: ['680px', '140px'],
	    offset: [($(window).height())/4-70+'px',''],  
	    end: function(){
	        mypop = null
	    }
	});
	$(window).on('resize', function(){
	    if(mypop){
	        layer.area(mypop, {
	            top: ($(window).height())/4-70
	        });
	    }
	});


}
</script>
</head>

<body>
<div class="admin_table">
<div class="content_box">
  <div class="btn_box">

  		<input id="add_bt" type="button" value="添加" class="initial" onclick="javascript:location.href='${ctx}/admin/cdInformation/add/new.html'"/>
     
  </div>
   <div class="list_info">
  	<form id="search_form" action="${ctx}/admin/cdInformation/list/1.html" method="post">
  	 <h2>产品信息设置</h2>
  	 <div class="div_input">
  	 <em>产品编号：</em><input type="text" id="fcpbh" name="fcpbh" value="${fcpbh}"  class="input_a1" size="30"/>
  	 <em>产品名：</em><input type="text" id="fcdname" name="fcdname" value="${fcdname}"  class="input_a1" size="30"/>
  	  <input id="add_bt" type="button" value="查询" class="initial" onclick="find();"/>
  	  <input id="add_bt" type="button" value="导入CSV" class="initial" style="cursor:hand" onclick="loadPageLayer('导入产品信息','${ctx}/admin/cdInformation/Find.html');"/> 
  	</div>
  	 </form>
   
 
  <br/>
  <table width="98%" border="0" cellpadding="0" cellspacing="1">
	  <thead>
	  	<tr>
	  	     <th>序号</th>
	  	     <th>产品编号</th> 
	  		 <th>产品名</th> 
	  		 <th>商品类别</th> 
	  		 <th>商品价格</th> 
	  		 <th>货架号</th> 
	  		 <th>库存</th>
	  		 
	        <th width="8%">操作</th>
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
	      <tr ondblclick="javascript:location.href='${ctx}/admin/cdInformation/add/new.html?id=${mb.id}'">
	        <td>${sta.index + 1}</td>
	      	<td>${mb.cpbh}</td>
	      	<td>${mb.cdname}</td>
	      	<td>${mb.name}</td>
	      	<td>${mb.cdprice}</td>
	      	<td>${mb.shelfnumber}</td>
	      	<td>${mb.stockr}</td>
	        <td>
	          	<c:if test="${mb_updt == true}">
		          	<div class="btn_icon">
		          	 <input type="image" src="${ctx}/theme/default/images/edit_icon.png" title="修改" onclick="javascript:location.href='${ctx}/admin/cdInformation/add/new.html?id=${mb.id}'"/>
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
			<td colspan="8">
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
