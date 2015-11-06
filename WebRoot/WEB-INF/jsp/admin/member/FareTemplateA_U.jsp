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
<script type="text/javascript" src="${ctx}/js/layer/layer.min.js"></script>
<script type="text/javascript" >
var ctx="${ctx}";
var mypop ;
$(document).ready(function(){
$("#dispatchTime option[value='${model.DispatchTime}']").attr("selected", true);
$("input[name='isFreePostage'][value='${model.IsFreePostage}']").attr("checked", "checked");
$("input[name='valuationModel'][value='${model.ValuationModel}']").attr("checked", "checked");
});
$(window).bind('resize', function (){
	 var wh=$(window).height();
		var ww=$(window).width();
	try{ 
		layer.area(mypop, {width:ww-50, height:  wh-50});
	}catch(e){}
	  });
</script>
<script type="text/javascript" src="${ctx}/theme/admin/member/FareTemplateA_U.js"></script>
<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>

</head>

<body>



<div class="admin_table">
<form id="add_form" action="${ctx }/admin/fareTemplate/save.html" method="post" onsubmit="return onsubmitClick();">

  <div class="add_info">

   <h2>新增运费模版</h2>
    <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
    <tr>
     <th class="w100" width="70px">模版名称:</th>
     <td>
     	<input id="name" name="name" type="text" value="${model.Name}" class="input_a1"  />&nbsp;<font color="red">*</font>
     </td>
         </tr>
            <tr>
     <th class="w100">宝贝地址:</th>
     <td>
     <c:if test="${model!=null}">
     <input id="shopAddr" name="shopAddr" type="text" value="${model.ShopAddr}" class="input_a1" readonly="readonly" />
     </c:if>
     <c:if test="${model==null}">
     <input id="shopAddr" name="shopAddr" type="text" value="${shopAddr}" class="input_a1" readonly="readonly" />
     </c:if>
     &nbsp;<font color="red">*</font>(发货地址)<a href="javascript:void(0);" onclick="showLayer('${ctx}/admin/fareTemplate/citySelected.html','shopAddr')">编辑地址</a>
     </td>
         </tr>
            <tr>
     <th class="w100">发货时间:</th>
     <td>
     <select name="dispatchTime" id="dispatchTime">
    
      <option value="12">12小时内</option>
       <option value="24">24小时内</option>
         <option value="48">48小时内</option>
         <option value="72" selected="selected">72小时内</option>
          <option value="120">5天内</option>
            <option value="168">7天内</option>
              <option value="360">15天内</option>
                <option value="630">30天内</option>    <option value="960">40天内</option>
                
     </select>&nbsp;<font color="red">*</font>
     	承诺买家付款后该时间内录入物流信息并发货，以物流信息收单信息为准 。
     </td>
         </tr>
            <tr>
     <th class="w100">是否包邮:</th>
     <td>
     	 <input  name="isFreePostage" type="radio" value="1" class="input_a1" checked="checked"/>买家承担运费 <input  name="isFreePostage" type="radio" value="2" class="input_a1" />卖家承担运费
     </td>
         </tr>
                 <tr>
     <th class="w100">计算方式:</th>
     <td>
  <input  name="valuationModel" type="radio" value="1" class="input_a1"  checked="checked"/>按件数 <input  name="valuationModel" type="radio" value="2" class="input_a1" />按重量  <input  name="valuationModel" type="radio" value="3" class="input_a1" />按体积
     </td>
         </tr>
                 <tr>
     <th class="w100">运送方式:</th>
     <td>
     请选择并添加运费方式(提示：除指定地区外，其余地区的运费采用“<b>默认运费</b>”)：<br/>
     
     	 <input  type="checkbox" name="mailCheckbox"  class="input_a1" onclick="mailCheckboxOnclick(this)"/>平邮
     	 <div id="mailMode" >
     	 
     	 </div><br/>
     	 <input  type="checkbox" name="expressCheckbox" class="input_a1" onclick="expressCheckboxOnclick(this)"/>快递公司
     	  <div id="expressMode" >
     	 </div>
     	 <br/>
     	 <input  type="checkbox" name="emsCheckbox" class="input_a1" onclick="emsCheckboxOnclick(this)"/>EMS
     	  <div id="emsMode"  >
     	 </div>
     </td>
         </tr>
   </table>
     
   
       <div class="div_submit">
					<input id="sumbit_bt" name="" type="submit" value="保存"
						class="photo_btn" />
					<input type="reset" value="返回" onclick="javascript:location.href='${ctx}/admin/fareTemplate/list/1.html'"
						class="photo_btn" />
				</div>
  
  </div>
  </form>
</div>

</body>
</html>