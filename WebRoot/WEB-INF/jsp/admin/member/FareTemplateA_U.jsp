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

<%--<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
--%>
<script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/theme/js/city.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/theme/js/jquery.cityselect.js"></script>
<script type="text/javascript">
$(function(){
$("#city_3").citySelect({
    	prov:"广东", 
    	city:"广州"
	});
	
	
	
});
////////////////平邮/////////////////
function mailCheckboxOnclick(obj){
	if(obj.checked){
		$("#mailMode").css("display","");
		}else{
			$("#mailMode").css("display","none");
			}
}
function deleteMailTr(no){
	 $("#trMail"+no).remove();
	// trMailCount=trMailCount-1;
}
var trMailNo=0;
//var trMailCount=0;
function addMailTr(){
	var tbody = $("#mailBody"); 
	trMailNo=trMailNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trMail"+trMailNo+"\"> ";
	newRow=newRow.concat("<td><input name=\"mailRegion"+trMailNo+"\" type=\"text\" value=\"广东省-广州市\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailFirst"+trMailNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailFirstAmount"+trMailNo+"\" type=\"text\"  class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"mailSecond"+trMailNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailSecondAmount"+trMailNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteMailTr("+trMailNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
////////////////快递/////////////////
function expressCheckboxOnclick(obj){
	if(obj.checked){
		$("#expressMode").css("display","");
		}else{
			$("#expressMode").css("display","none");
			}
}
function deleteExpressTr(no){
	 $("#trExpress"+no).remove();
	 //trMailCount=trMailCount-1;
}
var trExpressNo=0;
//var trMailCount=0;
function addExpressTr(){
	var tbody = $("#expressBody"); 
	trExpressNo=trExpressNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trExpress"+trExpressNo+"\"> ";
	newRow=newRow.concat("<td><input name=\"expressRegion"+trExpressNo+"\" type=\"text\" value=\"广东省-广州市\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"expressFirst"+trExpressNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"expressFirstAmount"+trExpressNo+"\" type=\"text\"  class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"expressSecond"+trExpressNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"expressSecondAmount"+trExpressNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteExpressTr("+trExpressNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
////////////////EMS/////////////////
function emsCheckboxOnclick(obj){
	if(obj.checked){
		$("#emsMode").css("display","");
		}else{
			$("#emsMode").css("display","none");
			}
}
function deleteEmsTr(no){
	 $("#trEms"+no).remove();
	// trMailCount=trMailCount-1;
}
var trEmsNo=0;
//var trMailCount=0;
function addEmsTr(){
	var tbody = $("#emsBody"); 
	trEmsNo=trEmsNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trEms"+trEmsNo+"\"> ";
	newRow=newRow.concat("<td><input name=\"mailRegion"+trEmsNo+"\" type=\"text\" value=\"广东省-广州市\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailFirst"+trEmsNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailFirstAmount"+trEmsNo+"\" type=\"text\"  class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"mailSecond"+trEmsNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailSecondAmount"+trEmsNo+"\" type=\"text\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteEmsTr("+trEmsNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
</script>
<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>

</head>

<body>



<div class="admin_table">
<form id="add_form" action="${ctx }/admin/fareTemplate/save.html" method="post">

  <div class="add_info">

   <h2>新增运费模版</h2>
    <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
    <tr>
     <th class="w100">模版名称:</th>
     <td>
     	<input id="name" name="name" type="text" value="" class="input_a1"  />
     </td>
         </tr>
            <tr>
     <th class="w100">宝贝地址:</th>
     <td>
     	 <div id="city_3"><select class="prov" name="provno"  id="provno"></select>省<select class="city" name="cityno"  id="cityno" disabled="disabled"></select>市
   (发货地址) </div>
     </td>
         </tr>
            <tr>
     <th class="w100">发货时间:</th>
     <td>
     <select name="dispatchTime">
     <option value="">请选择</option>
      <option value="12">12小时内</option>
       <option value="24">24小时内</option>
         <option value="48">48小时内</option>
         <option value="72" selected="selected">72小时内</option>
          <option value="120">5天内</option>
            <option value="168">7天内</option>
              <option value="360">15天内</option>
                <option value="630">30天内</option>    <option value="960">40天内</option>
                
     </select>
     	承诺买家付款后该时间内录入物流信息并发货，以物流信息收单信息为准 。
     </td>
         </tr>
            <tr>
     <th class="w100">是否包邮:</th>
     <td>
     	 <input  name="shopAddr" type="radio" value="1" class="input_a1" checked="checked"/>买家承担运费 <input  name="shopAddr" type="radio" value="2" class="input_a1" />卖家承担运费
     </td>
         </tr>
                 <tr>
     <th class="w100">计算方式:</th>
     <td>
  <input  name="valuationModel" type="radio" value="1" class="input_a1"  checked="checked"/>按件数 <input  name="valuationModel" type="radio" value="2" class="input_a1" />按重量
     </td>
         </tr>
                 <tr>
     <th class="w100">运送方式:</th>
     <td>
     请选择并添加运费方式(提示：除指定地区外，其余地区的运费采用“默认运费”)：<br/>
     
     	 <input  type="checkbox" name="mailCheckbox"  class="input_a1" onclick="mailCheckboxOnclick(this)"/>平邮
     	 <div id="mailMode" style="display:none;">首件(重(kg)或体积( m³))：<input  type="text" name="mailDefalutFirst" value="" class="input_a1" />，首费<input   name="mailDefalutFirstAmount" type="text" value="0.0" class="input_a1" />元，继件(重或体积)<input name="mailDefalutSecond" type="text" value="0.0" class="input_a1" />，增加运费<input name="mailDefalutSecondAmount"  type="text" value="0.0" class="input_a1" />元<br/>
     	  <input  type="button" value="为指定地区设置运费" class="input_a1" onclick="addMailTr()"/>
     	 <table width="100%"><thead><tr><th>运送地区</th><th>首件(重(kg)或体积( m³))</th><th>首费(元)</th><th>继件(重或体积)</th><th>继费</th><th>操作</th></tr></thead>
     	 <tbody id="mailBody">
											
		</tbody>
     	 </table>
     	 </div><br/>
     	 <input  type="checkbox" name="expressCheckbox" class="input_a1" onclick="expressCheckboxOnclick(this)"/>快递公司
     	  <div id="expressMode" style="display:none;" >首件(重(kg)或体积( m³))：<input  type="text" name="expressDefalutFirst" value="" class="input_a1" />，首费<input   name="expressDefalutFirstAmount" type="text" value="0.0" class="input_a1" />元，继件(重或体积)<input name="expressDefalutSecond" type="text" value="0.0" class="input_a1" />，增加运费<input name="expressDefalutSecondAmount"  type="text" value="0.0" class="input_a1" />元<br/>
     	  <input  type="button" value="为指定地区设置运费" class="input_a1" onclick="addExpressTr()" />
     	   <table width="100%"><thead><tr><th>运送地区</th><td>首件(重(kg)或体积( m³))</td><td>首费(元)</td><td>继件(重或体积)</td><td>继费</td><td>操作</td></tr></thead>
     	 <tbody id="expressBody">
											
		</tbody>
		</table>
     	 </div>
     	 <br/>
     	 <input  type="checkbox" name="emsCheckbox" class="input_a1" onclick="emsCheckboxOnclick(this)"/>EMS
     	  <div id="emsMode" style="display:none;" >首件(重(kg)或体积( m³))：<input  type="text" name="emsDefalutFirst" value="" class="input_a1" />，首费<input   name="emsDefalutFirstAmount" type="text" value="0.0" class="input_a1" />元，继件(重或体积)<input name="emsDefalutSecond" type="text" value="0.0" class="input_a1" />，增加运费<input name="emsDefalutSecondAmount"  type="text" value="0.0" class="input_a1" />元<br/>
     	  <input  type="button" value="为指定地区设置运费" class="input_a1" onclick="addEmsTr()"/>
     	    <table width="100%"><thead><tr><td>运送地区</td><td>首件(重(kg)或体积( m³))</td><td>首费(元)</td><td>继件(重或体积)</td><td>继费</td><td>操作</td></tr></thead>
     	 <tbody id="emsBody">
											
		</tbody>
		</table>
     	 </div>
     </td>
         </tr>
   </table>
     
   
       <div class="div_submit">
					<input id="sumbit_bt" name="" type="submit" value="保存"
						class="photo_btn" />
					<input type="reset" value="返回" onclick="javascript:history.back(-1);"
						class="photo_btn" />
				</div>
  
  </div>
  </form>
</div>

</body>
</html>