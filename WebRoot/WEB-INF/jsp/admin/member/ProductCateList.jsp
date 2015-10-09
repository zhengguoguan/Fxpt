<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- bootstrap - css -->
<link href="${ctx}/res/bjui/themes/css/bootstrap.min.css" rel="stylesheet">
<!-- core - css -->
<link href="${ctx}/res/bjui/themes/css/style.css" rel="stylesheet">
<link href="${ctx}/res/bjui/themes/purple/core.css" id="bjui-link-theme" rel="stylesheet">
<!-- plug - css -->
<link href="${ctx}/res/bjui/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="${ctx}/res/bjui/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="${ctx}/res/bjui/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="${ctx}/res/bjui/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="${ctx}/res/bjui/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
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

<!-- init -->
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


</script>
<!-- for doc begin -->
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
<!-- for doc end -->
<!-- for git 挂件 begin -->
<style type="text/css">
    .pro_name a{color:#4183c4;}
    .osc_git_title{background-color:#d8e5f1;}
    .osc_git_box{background-color:#fafafa;}
    .osc_git_box{border-color:#ddd;}
    .osc_git_info{color:#666;}
    .osc_git_main a{color:#4183c4; font-size:14px; line-height:1.5;}
    .osc_git_main li{line-height:1.5;}
</style>

<script type="text/javascript">

 var $panel = $.CurrentNavtab;
	var t = 2;
    var $t = $('#ztree1');
     var $span  = $('#ztree-'+ t);
     var $log   = $('#ztree-log');
     var op     = $t.data();
        var edit   = op.showRenameBtn=false;
                 var rename = op.showRenameBtn=false;
                var remove = op.showRemoveBtn=false;
                
            $t.data('editEnable', edit).data('showRenameBtn', rename).data('showRemoveBtn', remove)
            
            $log.val('data-edit-enable="'+ edit +'"\r\n' + 'data-show-rename-btn="'+ rename +'"\r\n' + 'data-show-remove-btn="'+ remove +'"')
            $span.text(($span.text() == '无') ? '显示' : '无')
            $panel.initui();

//删除前事件
function M_BeforeRemove(treeId, treeNode) {
	alertMsg.confirm("确定删除 "+ treeNode.name +" 吗?", {
		okCall: function(){
			ajaxPost("${ctx}/admin/productCate/ProductCateDelete.html","id="+treeNode.id, "");
			 return true;
		}
	});
   return false;
}

//删除结束事件
function M_NodeRemove(event, treeId, treeNode) {
   alert("2");
}
function M_BeforeRename(event, treeId, treeNode) {
    alert("a");
}

//单击事件
function ZtreeClick(event, treeId, treeNode) {
	$(this).dialog({id:"productCateEdit"+treeNode.id, url:'${ctx}/admin/productCate/ProductCateEdit.html?id='+treeNode.id, title:"修改",mask:true});
}
</script>
<div class="bjui-pageHeader">
        <ul class="bjui-searchBar">
            <li>
			<a href="${ctx}/admin/productCate/add/new.html" class="btn btn-default" data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-mask" data-mask="true">添加</a>
			</li>
        </ul>
</div>
<div class="bjui-pageContent">
    <div class="pageFormContent" data-layout-h="0">
        <div style="float: left; width: 100%;">
            <fieldset>
                <div class="clearfix">
                     <div style="float:left; width:500px; height:450px; overflow:auto;">
                        <ul id="ztree1" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="ZtreeClick">
                            ${zNodes}
                        </ul>
                    </div>
                </div>
            </fieldset>
        </div>
    </div>
</div>