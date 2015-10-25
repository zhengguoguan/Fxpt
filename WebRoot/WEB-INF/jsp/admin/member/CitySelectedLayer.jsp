<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'CitySelectedLayer.jsp' starting page</title>
    <link href="${ctx}/adminthemes/css/style.css" type="text/css"
			rel="stylesheet" />
		<link href="${ctx}/adminthemes/default/css/master.css"
			rel="stylesheet" type="text/css" />
		<link href="${ctx}/adminthemes/default/css/default.css"
			rel="stylesheet" type="text/css" />
   <script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/theme/js/city.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.cityselect.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script>
<script type="text/javascript">
$(function(){
$("#city_3").citySelect({
    	prov:"广东", 
    	city:"广州"
	});
});
function selectAddr(){
	var provno=$("#provno").val();
	var cityno=$("#cityno").val();
	if(provno==""||cityno==""){
		alert("省与市都不能为空!");
		return false;
		}else{
			self.parent.selectAddr(provno+"省-"+cityno+"市","${addrId}");
			var index = parent.layer.getFrameIndex(window.name);
 			parent.layer.close(index);
			}
	
}
</script>
  </head>
  
  <body>
  <div class="admin_table">


  <div class="add_info">
      <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
					  <tr>
     <th class="w100" width="70px">选择省市:</th>
     <td>
     		 <div id="city_3"><select class="prov" name="provno"  id="provno"></select>省<select class="city" name="cityno"  id="cityno" disabled="disabled"></select>市
    </div>
     </td>
         </tr>
					</table>
					   <div class="div_submit">
					<input id="sumbit_bt" name="" type="submit" value="确认选中" onclick="selectAddr()"
						class="photo_btn" />
				
				</div>
  </div>
  </div>
   
    
  </body>
</html>
