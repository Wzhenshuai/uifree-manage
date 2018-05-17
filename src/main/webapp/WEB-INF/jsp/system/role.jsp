<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="wetechfn" uri="http://wetech.tech/admin/tags/wetech-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    ul.ztree {
        border: 1px solid #ccc;
        background-color: #fff;
        height: 200px;
        overflow-y: scroll;
        overflow-x: auto;
    }
</style>
<!-- Content Header (Page header) -->
<section class="content-header" style="">
    <h1>
        角色管理
        <small>用户的角色管理页面</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">用户管理</a></li>
        <li class="active">角色管理</li>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="btn-group btn-group-sm" id="toolbar">
                        <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
                        <shiro:hasPermission name="role:create">
                        <button type="button" id="addBtn" class="btn btn-default" data-toggle="modal"
                                data-target="#addModal" data-action="{before:'addBefore'}"><i class="glyphicon glyphicon glyphicon-plus"></i> 新增
                        </button>
                        </shiro:hasPermission>
                        <!-- Indicates caution should be taken with this action -->
                        <shiro:hasPermission name="role:update">
                        <button type="button" id="editBtn" class="btn btn-default" data-toggle="modal"
                                data-target="#editModal"
                                data-action="{type:'editable',form:'#editForm',table:'#table',after:'editAfter'}"
                                disabled><i class="glyphicon glyphicon glyphicon-edit"></i> 修改
                        </button>
                        </shiro:hasPermission>
                        <!-- Indicates a dangerous or potentially negative action -->
                        <shiro:hasPermission name="role:delete">
                        <button type="button" id="deleteBtn" class="btn btn-default" data-toggle="modal"
                                data-target="#deleteModal"
                                data-action="{type:'delete',form:'#deleteForm',idField:'id',table:'#table'}" disabled><i
                                class="glyphicon glyphicon-remove"></i> 删除
                        </button>
                        </shiro:hasPermission>
                    </div>
                    <table id="table"></table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>
<!-- /.content -->

<!-- add Modal -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addModalLabel">添加角色</h4>
            </div>
            <div class="modal-body">
                <form id="addForm">
                    <div class="form-group">
                        <label class="control-label" for="role"><span class="asterisk">*</span>角色名:</label>
                        <input id="role" type="text" class="form-control" name="role" placeholder="输入角色名" minlength="2" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="description">角色描述:</label>
                        <input id="description" type="text" class="form-control" name="description" placeholder="输入角色描述">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="resourceIds"><span class="asterisk">*</span>拥有的资源列表:</label>
                        <input type="hidden" id="resourceIds" name="resourceIds" required>
                        <input type="text" class="form-control" id="resourceName" name="resourceNames" readonly required>
                        <div class="help-block with-errors"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" form="addForm" class="btn btn-primary"
                        data-action="{type:'submit',form:'#addForm',url:'<%=request.getContextPath()%>/role/create',after:'$.myAction.refreshTable'}">
                    确定
                </button>
            </div>
        </div>
    </div>
</div>
<!-- edit Modal -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editModalLabel">修改角色</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id"/>
                    <div class="form-group">
                        <label class="control-label" for="role"><span class="asterisk">*</span>角色名:</label>
                        <input id="editRole" type="text" class="form-control" name="role" placeholder="输入角色名" minlength="2" required>
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="description">角色描述:</label>
                        <input id="editDescription" type="text" class="form-control" name="description" placeholder="输入角色描述">
                        <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="resourceIds"><span class="asterisk">*</span>拥有的资源列表:</label>
                        <input type="hidden" id="editResourceIds" name="resourceIds" required>
                        <input type="text" class="form-control" id="editResourceName" name="resourceNames" readonly required>
                        <div class="help-block with-errors"></div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" form="editForm" class="btn btn-primary"
                        data-action="{type:'submit',form:'#editForm',url:'<%=request.getContextPath()%>/role/update',after:'$.myAction.refreshTable'}">
                    确定
                </button>
            </div>
        </div>
    </div>
</div>

<!-- delete Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteSmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="deleteSmallModalLabel">删除角色</h4>
            </div>
            <div class="modal-body">
                <form id="deleteForm"></form>
                确定要删除选中的 <span class="records"></span> 条记录?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" form="deleteForm" class="btn btn-primary"
                        data-action="{type:'submit',form:'#deleteForm',url:'<%=request.getContextPath()%>/role/delete',after:'$.myAction.refreshTable'}">
                    确定
                </button>
            </div>
        </div>
    </div>
</div>

<!-- zTree -->
<div id="menuContent" class="menuContent" style="display:none;z-index:1989101600;position: absolute;">
    <ul id="tree" class="ztree" style="margin-top:0;"></ul>
</div>
<script>

    var $table = $('#table');

    $(function () {
        // bootstrap table初始化
        // http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
        $table.bootstrapTable({
            url: path + '/role/list',
            idField: 'id',
            searchOnEnterKey: false,
            columns: [
                {field: 'state', checkbox: true},
                {field: 'id', title: '编号', sortable: true, halign: 'left'},
                {field: 'role', title: '角色名称', sortable: true, halign: 'left'},
                {field: 'description', title: '角色描述', sortable: true, halign: 'left'},
                {field: 'resourceNames', title: '拥有的资源', sortable: true, halign: 'left'},
                {
                    field: 'action',
                    title: '操作',
                    halign: 'center',
                    align: 'center',
                    formatter: 'actionFormatter',
                    events: 'actionEvents',
                    clickToSelect: false
                }
            ]
        });
    });

    // 数据表格展开内容
    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    function addBefore(obj) {
        $.fn.zTree.getZTreeObj("tree").destroy();
        $.fn.zTree.init($("#tree"), setting, zNodes);
    }

    function editAfter(obj,row) {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        treeObj.destroy();
        $.fn.zTree.init($("#tree"), setting, zNodes);
        treeObj.expandAll(true);
    }

    function actionFormatter(value, row, index) {
        return '\
        <a class="like" href="javascript:void(0)" data-toggle="tooltip" title="Like"><i class="glyphicon glyphicon-heart"></i></a>　\
        <shiro:hasPermission name="role:update">\
        <a class="edit ml10" href="javascript:void(0)" data-toggle="tooltip" title="编辑"><i class="glyphicon glyphicon-edit"></i></a>　\
        </shiro:hasPermission>\
        <shiro:hasPermission name="role:delete">\
        <a class="remove ml10" href="javascript:void(0)" data-toggle="tooltip" title="删除"><i class="glyphicon glyphicon-remove"></i></a>　\
        </shiro:hasPermission>\
        ';
    }

    window.actionEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like icon, row: ' + JSON.stringify(row));
            console.log(value, row, index);
        },
        'click .edit': function (e, value, row, index) {
            $('#editModal').modal('show')
            $('#editForm').fillForm(row);
            console.log(value, row, index);
        },
        'click .remove': function (e, value, row, index) {
            $('#deleteModal').modal('show');
            $('.records').html('1');
            var html = '';
            for (var key in row) {
                html += '<input type="hidden" name="' + key + '" value="' + row[key] + '">';
            }
            $('#deleteForm').html(html);
        }
    };

    var setting = {
        check: {
            enable: true,
            chkboxType: {"Y": "", "N": ""}
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };

    var zNodes = [
        <c:forEach items="${resourceList}" var="r">
        <c:if test="${not r.rootNode}">
        {id:${r.id}, pId:${r.parentId}, name: "${r.name}", checked:${wetechfn:in(role.resourceIds, r.id)}},
        </c:if>
        </c:forEach>
    ];

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getCheckedNodes(true),
            id = "",
            name = "";
        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });
        for (var i = 0, l = nodes.length; i < l; i++) {
            id += nodes[i].id + ",";
            name += nodes[i].name + ",";
        }
        if (id.length > 0) id = id.substring(0, id.length - 1);
        if (name.length > 0) name = name.substring(0, name.length - 1);
        $("#resourceIds").val(id);
        $("#resourceName").val(name);
        $("#editResourceIds").val(id);
        $("#editResourceName").val(name);
        // hideMenu();
    }

    function showMenu() {
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var cityObj = $("#resourceName");
        var cityOffset = $("#resourceName").offset();
        $("#menuContent").css({left: cityOffset.left + "px", top: cityOffset.top + cityObj.outerHeight()}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function showMenuOfEdit() {
        var resourceIds = $('#editResourceIds').val();
        var resourceIdList = resourceIds.split(',');
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        for (var i in resourceIdList) {
            var id = resourceIdList[i];
            var node = treeObj.getNodeByParam("id", id, null);
            if (node === null) continue;
            treeObj.checkNode(node, true, true);
        }
        var cityObj = $("#editResourceName");
        var cityOffset = $("#editResourceName").offset();
        $("#menuContent").css({left: cityOffset.left + "px", top: cityOffset.top + cityObj.outerHeight()}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }

    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }

    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
            hideMenu();
        }
    }

    $.fn.zTree.init($("#tree"), setting, zNodes);
    $("#resourceName").click(showMenu);
    $("#editResourceName").click(showMenuOfEdit);
</script>