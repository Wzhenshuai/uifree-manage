<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="wetechfn" uri="http://wetech.tech/admin/tags/wetech-functions" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!-- Content Header (Page header) -->
<section class="content-header" style="">
    <h1>
        日志管理
        <small>系统日志的管理页面</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="active">日志管理</li>
    </ol>
</section>

<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <!-- /.box-header -->
                <div class="box-body">
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
<script>

    var $table = $('#table');

    $(function () {
        // bootstrap table初始化
        // http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
        $table.bootstrapTable({
            url: path + '/log/list',
            idField: 'id',
            searchOnEnterKey: false,
            columns: [
                {field: 'state', checkbox: true},
                {field: 'id', title: '编号', sortable: true, halign: 'left'},
                {field: 'username', title: '用户', sortable: true, halign: 'left'},
                {field: 'ip', title: 'IP', sortable: true, halign: 'left'},
                {field: 'execDesc', title: '操作描述', sortable: true, halign: 'left'},
                {field: 'status', title: '状态', sortable: true, halign: 'left'},
                {field: 'createTime', title: '时间', formatter: 'timeFormatter', sortable: true, halign: 'left'},
                {field: 'execMethod', title: '执行方法', sortable: true, halign: 'left'},
                {field: 'execTime', title: '耗时', formatter: 'execTimeFormatter', sortable: true, halign: 'left'},
                {field: 'reqMethod', title: '请求方法', sortable: true, halign: 'left'},
                {field: 'reqUri', title: '请求URL', sortable: true, halign: 'left'},
                {field: 'args', title: '参数', sortable: true, halign: 'left'},
                {field: 'returnVal', title: '返回值', sortable: true, halign: 'left'}
            ]
        });
    });

    function timeFormatter(value, row, index) {
        return Utils.dateFormat.formatTimestamp(value, 'yyyy-MM-dd hh:mm:ss');
    }

    function execTimeFormatter(value, row, index) {
        return value +' ms';
    }

    // 数据表格展开内容
    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
</script>