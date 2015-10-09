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
<script type="text/javascript" src="${ctx}/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.select.js" charset="GBK"></script>
<script src="${ctx}/js/formValidator/jquery-1.4.4.min.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/js/formValidator/style/validatorTidyMode.css" />
<script src="${ctx}/js/formValidator/formValidator-4.0.1.js" type="text/javascript"></script>
<script src="${ctx}/js/formValidator/formValidatorRegex.js" type="text/javascript"></script> 
<script language="javascript" src="${ctx}/js/jsp/member/MemberBasicV.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx }/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="${ctx }/ckfinder/ckfinder.js"></script> 

<script type="text/javascript">
$(document).ready(function(){
	formInitConfig("add_form",3000);
	autoValidate();
		
});

</script>
<script type="text/javascript">
	var editor;
	editor= CKEDITOR.replace("content"); 
	CKFinder.setupCKEditor(editor, '${ctx}/js/ckfinder/');
	

	function BrowseServer()
{
	var finder = new CKFinder();
	finder.basePath = '${ctx}/js/ckfinder/';
	finder.selectActionFunction = SetFileField;
	finder.popup();
}

</script>

<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>

</head>

<body>

<div class="admin_table">
<form id="add_form" action='<c:if test="${model.id == null}">${ctx}/admin/mbCommission/save.html</c:if><c:if test="${model.id != null}">${ctx}/admin/mbCommission/update/${model.id}.html</c:if>' method="post">

  <div class="add_info">

   <h2>分销提成设置</h2>
    <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
    <tr>
     <th class="w100">分销级别:</th>
     <td>
     	<input id="mbtype" name="mbtype" type="text" value="${model.mbtype}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
         </tr>
            <tr>
     <th class="w100">分销提成(单位%):</th>
     <td>
     	 <input id="commission" name="commission" type="text" value="${model.commission}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
         </tr>
   </table>
     
   
       <div class="div_submit">
					<input id="sumbit_bt" name="" type="submit" value="提  交"
						class="photo_btn" />
					<input type="reset" value="返回" onclick="javascript:history.back(-1);"
						class="photo_btn" />
				</div>
  
  </div>
  </form>
</div>

</body>
</html>