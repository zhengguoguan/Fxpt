
function showLayer(url,addrId){
	url=url+"?addrId="+addrId
	 var wh=$(window).height();
		var ww=$(window).width();
		 mypop = $.layer({
		    type: 2,
		    title : false,
		    border: [10, 0.4, 'black',true],
		    closeBtn: [1, true],
		    iframe: {src : url},
		    move: false,
		    area: [ww-50, wh-50],
		    offset: ['20px', ''], //上下垂直居中
		    close: function(index){
		    	layer.close(index);
		    
		    }
		});

}
function selectAddr(addrString,addrId){
	$("#"+addrId).val(addrString);
}
////////////////平邮/////////////////
function mailCheckboxOnclick(obj){
	if(obj.checked){
		var tempText="首件(重(kg)或体积( m³))：<input  type=\"text\" name=\"mailDefalutFirst\" value=\"1\" class=\"input_a1\" size=\"10px\" />，首费<input   name=\"mailDefalutFirstAmount\" type=\"text\" value=\"\" class=\"input_a1\" size=\"10px\"/>元，继件(重或体积)<input name=\"mailDefalutSecond\" type=\"text\" value=\"1\" class=\"input_a1\" size=\"10px\"/>，增加运费<input name=\"mailDefalutSecondAmount\"  type=\"text\" value=\"0.0\" class=\"input_a1\" size=\"10px\"/>元<br/>";
		tempText+="  <a href=\"javascript:void(0)\" onclick=\"addMailTr()\">为指定地区设置运费</a>";
		tempText+=" <table width=\"100%\"><thead><tr><th>运送地区</th><th>首件(重(kg)或体积( m³))</th><th>首费(元)</th><th>继件(重或体积)</th><th>继费</th><th>操作</th></tr></thead>";
		tempText+=" <tbody id=\"bodyMail\">";											
		tempText+="</tbody> </table>";
		$("#mailMode").html(tempText);
		//$("#mailMode").css("display","");
		}else{
			$("#mailMode").html("");
			}
}
function deleteMailTr(no){
	 $("#trMail"+no).remove();
	// trMailCount=trMailCount-1;
}
var trMailNo=0;
//var trMailCount=0;
function addMailTr(){
	var tbody = $("#bodyMail"); 
	trMailNo=trMailNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trMail"+trMailNo+"\"> ";
	newRow=newRow.concat("<td><input readonly=\"readonly\" name=\"mailRegion"+trMailNo+"\" id=\"mailRegion"+trMailNo+"\" type=\"text\"   class=\"input_a1\" /> <a href=\"javascript:void(0);\" onclick=\"showLayer('${ctx}/admin/fareTemplate/citySelected.html','mailRegion"+trMailNo+"')\">编辑</a></td>");
	newRow=newRow.concat("<td><input name=\"mailFirst"+trMailNo+"\" type=\"text\"  value=\"1\" class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailFirstAmount"+trMailNo+"\" type=\"text\" value=\"\"  class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"mailSecond"+trMailNo+"\" type=\"text\" value=\"1\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"mailSecondAmount"+trMailNo+"\" type=\"text\" value=\"\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteMailTr("+trMailNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
////////////////快递/////////////////
function expressCheckboxOnclick(obj){
	if(obj.checked){
		var tempText="首件(重(kg)或体积( m³))：<input  type=\"text\" name=\"expressDefalutFirst\" value=\"1\" class=\"input_a1\" size=\"10px\" />，首费<input   name=\"expressDefalutFirstAmount\" type=\"text\" value=\"\" class=\"input_a1\" size=\"10px\"/>元，继件(重或体积)<input name=\"expressDefalutSecond\" type=\"text\" value=\"1\" class=\"input_a1\" size=\"10px\"/>，增加运费<input name=\"expressDefalutSecondAmount\"  type=\"text\" value=\"0.0\" class=\"input_a1\" size=\"10px\" />元<br/>";
     	
		tempText+=" <a href=\"javascript:void(0)\" onclick=\"addExpressTr()\">为指定地区设置运费</a>";
		tempText+=" <table width=\"100%\"><thead><tr><th>运送地区</th><th>首件(重(kg)或体积( m³))</th><th>首费(元)</th><th>继件(重或体积)</th><th>继费</th><th>操作</th></tr></thead>";
		tempText+="<tbody id=\"bodyExpress\">";
		tempText+="</tbody> </table>";
		$("#expressMode").html(tempText);
		}else{
			$("#expressMode").html("");
			}
}
function deleteExpressTr(no){
	 $("#trExpress"+no).remove();
	 //trMailCount=trMailCount-1;
}
var trExpressNo=0;
//var trMailCount=0;
function addExpressTr(){
	var tbody = $("#bodyExpress"); 
	trExpressNo=trExpressNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trExpress"+trExpressNo+"\"> ";
	newRow=newRow.concat("<td><input readonly=\"readonly\" name=\"expressRegion"+trExpressNo+"\" id=\"expressRegion"+trExpressNo+"\"  type=\"text\"   class=\"input_a1\" /><a href=\"javascript:void(0);\" onclick=\"showLayer('${ctx}/admin/fareTemplate/citySelected.html','expressRegion"+trExpressNo+"')\">编辑</a> </td>");
	newRow=newRow.concat("<td><input name=\"expressFirst"+trExpressNo+"\" type=\"text\" value=\"1\" class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"expressFirstAmount"+trExpressNo+"\" type=\"text\" value=\"\"  class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"expressSecond"+trExpressNo+"\" type=\"text\" value=\"1\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"expressSecondAmount"+trExpressNo+"\" type=\"text\" value=\"\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteExpressTr("+trExpressNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
////////////////EMS/////////////////
function emsCheckboxOnclick(obj){
	if(obj.checked){
var tempText="首件(重(kg)或体积( m³))：<input  type=\"text\" name=\"emsDefalutFirst\" value=\"1\" class=\"input_a1\" size=\"10px\" />，首费<input   name=\"emsDefalutFirstAmount\" type=\"text\" value=\"\" class=\"input_a1\" size=\"10px\"/>元，继件(重或体积)<input name=\"emsDefalutSecond\" type=\"text\" value=\"1\" class=\"input_a1\" size=\"10px\"/>，增加运费<input name=\"emsDefalutSecondAmount\"  type=\"text\" value=\"0.0\" class=\"input_a1\" size=\"10px\" />元<br/>";
     	
		tempText+=" <a href=\"javascript:void(0)\" onclick=\"addEmsTr()\">为指定地区设置运费</a>";
		tempText+=" <table width=\"100%\"><thead><tr><th>运送地区</th><th>首件(重(kg)或体积( m³))</th><th>首费(元)</th><th>继件(重或体积)</th><th>继费</th><th>操作</th></tr></thead>";
		tempText+="<tbody id=\"bodyEms\">";
		tempText+="</tbody> </table>";
		$("#emsMode").html(tempText);
		}else{
			$("#emsMode").html("");
			}
}
function deleteEmsTr(no){
	 $("#trEms"+no).remove();
	// trMailCount=trMailCount-1;
}
var trEmsNo=0;
//var trMailCount=0;
function addEmsTr(){
	var tbody = $("#bodyEms"); 
	trEmsNo=trEmsNo+1;
	//trMailCount=trMailCount+1;
	var newRow="<tr id=\"trEms"+trEmsNo+"\"> ";
	newRow=newRow.concat("<td><input readonly=\"readonly\" name=\"emsRegion"+trEmsNo+"\" id=\"emsRegion"+trEmsNo+"\" type=\"text\"   class=\"input_a1\" /> <a href=\"javascript:void(0);\" onclick=\"showLayer('${ctx}/admin/fareTemplate/citySelected.html','emsRegion"+trEmsNo+"')\">编辑</a></td>");
	newRow=newRow.concat("<td><input name=\"emsFirst"+trEmsNo+"\" type=\"text\" value=\"1\" class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"emsFirstAmount"+trEmsNo+"\" type=\"text\" value=\"\" class=\"input_a1\" />  </td>");
	newRow=newRow.concat("<td> <input name=\"emslSecond"+trEmsNo+"\" type=\"text\"  value=\"1\" class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><input name=\"emsSecondAmount"+trEmsNo+"\" type=\"text\" value=\"\"  class=\"input_a1\" /> </td>");
	newRow=newRow.concat("<td><a href=\"javascript:void(0)\" onclick=\"deleteEmsTr("+trEmsNo+")\">删除</a> </td>");
	newRow=newRow.concat("</tr>");
	tbody.html(tbody.html().concat(newRow)); 
}
//验证输入框的值
function onsubmitClick(){
	var nameList=new Array("mail","express","ems");
	var onReturn=true;
	for(var i = 0;i < nameList.length; i++) {
		if(onReturn==false){
			 break;
		}
		  $(":text[name^='"+nameList[i]+"']").each(function(){
			
		       if(trim($(this).val())==""||isNaN($(this).val())){
		    	   onReturn= false;
		    	   alert("此处必须为数字");
		    	   $(this).focus();
		    	  return;
		       }
		    });
		 
		}
	 return false;
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}