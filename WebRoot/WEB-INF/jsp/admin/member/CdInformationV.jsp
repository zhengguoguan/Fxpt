<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<script type="text/javascript" src="${ctx}/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="${ctx}/js/ckfinder/ckfinder.js"></script> 
		<link href="${ctx}/res/bjui/themes/css/bootstrap.min.css" rel="stylesheet">
<!-- core - css -->
<link href="${ctx}/res/bjui/themes/css/style.css" rel="stylesheet">

<!--[if lte IE 7]>
<link href="${ctx}/res/bjui/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
    <script src="${ctx}/res/bjui/other/html5shiv.min.js"></script>
    <script src="${ctx}/res/bjui/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script src="${ctx}/res/bjui/js/jquery-1.7.2.min.js"></script>
<script src="${ctx}/res/bjui/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script src="${ctx}/res/bjui/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<!-- BJUI.all 分模块压缩版 -->
<script src="${ctx}/res/bjui/js/bjui-all.js"></script>
<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script src="${ctx}/res/bjui/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script src="${ctx}/res/bjui/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script src="${ctx}/res/bjui/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- colorpicker -->
<script src="${ctx}/res/bjui/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="${ctx}/res/bjui/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="${ctx}/res/bjui/plugins/niceValidator/jquery.validator.js"></script>
<script src="${ctx}/res/bjui/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="${ctx}/res/bjui/plugins/bootstrap.min.js"></script>
<script src="${ctx}/res/bjui/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<!-- icheck -->
<script src="${ctx}/res/bjui/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script src="${ctx}/res/bjui/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- HighCharts -->
<script src="${ctx}/res/bjui/plugins/highcharts/highcharts.js"></script>
<script src="${ctx}/res/bjui/plugins/highcharts/highcharts-3d.js"></script>
<script src="${ctx}/res/bjui/plugins/highcharts/themes/gray.js"></script>
<!-- ECharts -->
<script src="${ctx}/res/bjui/plugins/echarts/echarts.js"></script>
<!-- other plugins -->
<script src="${ctx}/res/bjui/plugins/other/jquery.autosize.js"></script>
<link href="${ctx}/res/bjui/plugins/uploadify/css/uploadify.css" rel="stylesheet">
<script src="${ctx}/res/bjui/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
   <link type="text/css" rel="stylesheet" href="${ctx}/res/bjui/js/syntaxhighlighter-2.1.382/styles/shCore.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/res/bjui/js/syntaxhighlighter-2.1.382/styles/shThemeEclipse.css"/>
<script type="text/javascript" src="${ctx}/res/bjui/js/syntaxhighlighter-2.1.382/scripts/brush.js"></script>
<link href="doc/doc.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
    SyntaxHighlighter.config.clipboardSwf = '${ctx}/res/bjui/js/syntaxhighlighter-2.1.382/scripts/clipboard.swf'
    $(document).on(BJUI.eventType.initUI, function(e) {
        SyntaxHighlighter.highlight();
    })
})
</script>
		<style>
.flexslider {
	margin: 0px auto 20px;
	position: relative;
	width: 100%;
	height: 482px;
	overflow: hidden;
	zoom: 1;
}

.flexslider .slides li {
	width: 100%;
	height: 100%;
}

.flex-direction-nav a {
	width: 70px;
	height: 70px;
	line-height: 99em;
	overflow: hidden;
	margin: -35px 0 0;
	display: block;
	background: url(${ctx}/js/images/ad_ctr.png) no-repeat;
	position: absolute;
	top: 50%;
	z-index: 10;
	cursor: pointer;
	opacity: 0;
	filter: alpha(opacity=0);
	-webkit-transition: all .3s ease;
	border-radius: 35px;
}

.flex-direction-nav .flex-next {
	background-position: 0 -70px;
	right: 0;
}

.flex-direction-nav .flex-prev {
	left: 0;
}

.flexslider:hover .flex-next {
	opacity: 0.8;
	filter: alpha(opacity=25);
}

.flexslider:hover .flex-prev {
	opacity: 0.8;
	filter: alpha(opacity=25);
}

.flexslider:hover .flex-next:hover,
.flexslider:hover .flex-prev:hover {
	opacity: 1;
	filter: alpha(opacity=50);
}

.flex-control-nav {
	width: 100%;
	position: absolute;
	bottom: 10px;
	text-align: center;
}

.flex-control-nav li {
	margin: 0 2px;
	display: inline-block;
	zoom: 1;
	*display: inline;
}

.flex-control-paging li a {
	background: url(${ctx}/js/images/dot.png) no-repeat 0 -16px;
	display: block;
	height: 16px;
	overflow: hidden;
	text-indent: -99em;
	width: 16px;
	cursor: pointer;
}

.flex-control-paging li a.flex-active,
.flex-control-paging li.active a {
	background-position: 0 0;
}

.flexslider .slides a img {
	width: 100%;
	height: 482px;
	display: block;
}
</style>
		
<script type="text/javascript">

$(function() {
    BJUI.init({
        JSPATH       : '${ctx}/res/bjui/',         //[可选]框架路径
        PLUGINPATH   : '${ctx}/res/bjui/plugins/', //[可选]插件路径
        loginInfo    : {url:'login_timeout.html', title:'登录', width:400, height:200}, // 会话超时后弹出登录对话框
        statusCode   : {ok:200, error:300, timeout:301}, //[可选]
        ajaxTimeout  : 50000, //[可选]全局Ajax请求超时时间(毫秒)
        alertTimeout : 30000, //[可选]信息提示[info/correct]自动关闭延时(毫秒)
        pageInfo     : {pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
        keys         : {statusCode:'statusCode', message:'message'}, //[可选]
        ui           : {showSlidebar:true, hideMode:'display'}, //[可选]hideMode:navtab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
        debug        : true,    // [可选]调试模式 [true|false，默认false]
        theme        : 'blue' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, green]
    })
    //时钟
    var today = new Date(), time = today.getTime()
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1))
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
    }, 1000)
})

$(document).ready(function(){
var picture="${model.cdpicture}";
var chpicture="${model.cdpicturech}";
var strs= new Array();
var chstrs= new Array();
strs=picture.split(","); //字符分割 
chstrs=chpicture.split(","); 
  var $br = "";  
        var $file = "";  
       var $button =""; 
        
for (i=0;i<strs.length ;i++ ) 
{ 
if(chstrs[i]!=""){
$file = $("<input type='text' name='oldchfiles'  value='"+strs[i]+"'/>"); 
if(i<chstrs.length)
{
$file1 = $("<input type='hidden' name='oldfiles'  value='"+strs[i]+"'/>"); 
$button = $("<input type='button' value='delete'>");      
$br = $("<br />");  
$(":input[type=button][value=more]").after($file).after($file1).after($button).after($br); 
}else{
$button = $("<input type='button' value='delete'>");      
$br = $("<br />");  
$(":input[type=button][value=more]").after($file).after($button).after($br); 
}


$button.bind("click",function(){  
           $(this).next(":hidden").next(":text").next("br").remove()
           $(this).next(":hidden").next(":text").remove()
           $(this).next(":hidden").remove()
              
           $(this).remove();
       })  
       }
} 

});

</script>
<script type="text/javascript">
$(document).ready(function(){
    $(":input[type=button][value=more]").bind("click",function(){  
       var $br = $("<br />");  
       var $file = $("<input type='file' name='files'  />");  
       var $button = $("<input type='button' value='delete'>");  
       $(this).after($file).after($button).after($br);  
       $button.bind("click",function(){  
           $br.remove();  
           $file.remove();  
           $button.remove();  
       })  
  })  
		
});

</script>
<script type="text/javascript">
function S_NodeCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId),
        nodes = zTree.getCheckedNodes(true)
    var ids = '', names = ''
    
    for (var i = 0; i < nodes.length; i++) {
        ids   += ','+ nodes[i].id
        names += ','+ nodes[i].name
    }
    if (ids.length > 0) {
        ids = ids.substr(1), names = names.substr(1)
    }
    
    var $from = $('#'+ treeId).data('fromObj')
    if ($from && $from.length) $from.val(names)
    $("#cdcategories").val(ids);
}
//单击事件
function S_NodeClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)
    
    zTree.checkNode(treeNode, !treeNode.checked, true, true)
    
    event.preventDefault()
}


</script>

<style type="text/css">
html { overflow:-moz-scrollbars-vertical;}
</style>

</head>

<body>

<div class="bjui-pageContent">
<form id="add_form"  enctype="multipart/form-data"  action='<c:if test="${model.id == null}">${ctx}/admin/cdInformation/save.html</c:if><c:if test="${model.id != null}">${ctx}/admin/cdInformation/update/${model.id}.html</c:if>' method="post">

  <div class="add_info">

   <h2>商品信息</h2>
    <table id="questTable" border="0" cellspacing="0" cellpadding="0"
					class="ListTable">
					
    <tr>
     <th class="w100">产品名:</th>
     <td>
     	<input id="cdname" name="cdname" type="text" value="${model.cdname}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
      <th class="w100">商品价格:</th>
     <td>
     	 <input id="cdprice" name="cdprice" type="text" value="${model.cdprice}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
         </tr>
         <tr>
     <th class="w100">商品编号:</th>
     <td>
     	<input id="cpbh" name="cpbh" type="text" value="${model.cpbh}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
      <th class="w100">库存:</th>
     <td>
     	 <input id="stockr" name="stockr" type="text" value="${model.stockr}" class="input_a1" maxlength="100" style="width:160px"  />
     </td>
         </tr>
          <tr>
         <th class="w100">商品大类:</th>
     <td>
         <input type="hidden" name="cdcategories" id="cdcategories" value="${model.cdcategories}">
                        	<input type="text" name="menus" id="fathername" data-toggle="selectztree" size="18" data-tree="#j_select_tree2" value="${fatherName}" readonly>
                                <ul id="j_select_tree2" class="ztree hide" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-chk-style="radio" data-radio-type="all" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
			                    ${zNodes}
			                    </ul>
        </td>	
        <th class="w100">货架号:</th>
     <td>
        <input id="shelfnumber" name="shelfnumber" type="text" value="${model.shelfnumber}" class="input_a1" maxlength="100" style="width:160px"  />
        </td>	
        </tr>
       
     <tr>
       
     <th class="w100">产品图片:</th>
     <td colspan="3">
     	<input style="border:0px;" type="file" name="files" id="files"/><input type="button" value="more" /> 
     	 
     </td>
     
         </tr>
         <tr>
          <th class="w100">产品预览:</th>
         
     <td colspan="3">

          <div id="banner_tabs" class="flexslider">
	<ul class="slides">
	<c:forEach items="${cdpicture}" var="mb" varStatus="sta"> 
	<li>
			<a title="" target="_blank" href="#">
				<img width="100%" height="482" alt="" style="background: url(${mb['cdpicture']}) no-repeat center;" src="${mb['cdpicture']}">
			</a>
		    </li>
            </c:forEach>
	</ul>
	<ul class="flex-direction-nav">
		<li><a class="flex-prev" href="javascript:;">Previous</a></li>
		<li><a class="flex-next" href="javascript:;">Next</a></li>
	</ul>
	<ol id="bannerCtrl" class="flex-control-nav flex-control-paging">
	<c:forEach items="${cdpicture}" var="mb" varStatus="sta" > 
	    <li><a>${sta.index+1}</a></li>
		</c:forEach>
	</ol>
</div>
<script src="${ctx}/js/slider.js"></script>
<script type="text/javascript">
$(function() {
	var bannerSlider = new Slider($('#banner_tabs'), {
		time: 5000,
		delay: 400,
		event: 'hover',
		auto: true,
		mode: 'fade',
		controller: $('#bannerCtrl'),
		activeControllerCls: 'active'
	});
	$('#banner_tabs .flex-prev').click(function() {
		bannerSlider.prev()
	});
	$('#banner_tabs .flex-next').click(function() {
		bannerSlider.next()
	});
})
</script>
          
     </td>
         </tr>
        
   </table>
   <div class="admin_table">
   <table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
          <th>简介</th>
	      <td colspan="3"><textarea id="cdintroduction" name="cdintroduction" rows="3" cols="120" >${model.cdintroduction}</textarea>
	      </td>
         </tr>
   </table>
   </div>
       <div class="div_submit">
					<input id="sumbit_bt" name="" type="submit" value="提  交"
						class="photo_btn" />
					<input type="reset" value="返回" onclick="javascript:history.back(-1);"
						class="photo_btn" />
				</div>
  
  </div>
  </form>
</div>
<script type="text/javascript">
	var editor;
	editor= CKEDITOR.replace("cdintroduction"); 
	CKFinder.setupCKEditor(editor, '${ctx}/js/ckfinder/');
	

	function BrowseServer()
{
	var finder = new CKFinder();
	finder.basePath = '${ctx}/js/ckfinder/';
	finder.selectActionFunction = SetFileField;
	finder.popup();
}
</script>
</body>
</html>