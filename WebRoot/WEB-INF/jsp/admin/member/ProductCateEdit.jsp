<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="${ctx}/js/jquery-1.8.3.js"></script>
 <script type="text/javascript" src="${ctx}/js/tab/content.js"></script>
<script type="text/javascript">

//选择事件
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
    $("#fatherId2").val(ids);
}
//单击事件
function S_NodeClick(event, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj(treeId)
    
    zTree.checkNode(treeNode, !treeNode.checked, true, true)
    
    event.preventDefault()
}
function openNew(iframe,plugin, url,id) {
  	//var obj=window.parent.document;
  	// obj.openNew(iframe,plugin, url);
  	//alert("d");
  //	self.parent.frames[iframe].openNew(plugin, url);
	  self.parent.openNew(plugin, url,id);
  }
function test(){
openNew('I2','类别设置','${ctx}/admin/productCate/list/1.html','Lev2_07');
}

</script>
<div class="bjui-pageContent">
    <form action="${ctx}/admin/productCate/ProductCateUpdate.html" method="post" class="pageForm" data-toggle="validate" data-reload-navtab="true" data-callback="test">
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover">
                <thead>
                    <tr>
                    	<td colspan="2" align="center"><h3>修改分类</h3></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td width="400">
                            <label for="fatherId" class="control-label x150">上级分类：</label>
                            <input type="hidden" name="id" id="id" value="${id}">
               				<input type="hidden" name="fatherid" id="fatherId2" value="${fatherid}">
                        	<input type="text" name="menus" id="fathername" data-toggle="selectztree" size="18" data-tree="#j_select_tree2" value="${fatherName}" readonly>
                                <ul id="j_select_tree2" class="ztree hide" data-toggle="ztree" data-expand-all="true" data-check-enable="true" data-chk-style="radio" data-radio-type="all" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
			                    ${zNodes}
			                    </ul>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="name" class="control-label x150">分类名称：</label>
                            <input type="text" name="name" id="name" data-rule="required" size="20" placeholder="请输入分类名称" value="${name}">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close">关闭</button></li>
                <li><a href="${ctx}/admin/productCate/ProductCateDelete.html?id=${productCate.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该分类吗？" data-callback="test">删除</a></li>
                <li><button type="submit" class="btn-default">保存</button></li>
            </ul>
        </div>
    </form>
</div>