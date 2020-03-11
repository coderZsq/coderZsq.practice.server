<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp"%>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp"%>
    <!--菜单回显-->
    <c:set var="currentMenu" value="permission"></c:set>
    <%@include file="/WEB-INF/views/common/menu.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="/permission/reload.do" class="btn btn-success btn-input" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 重新加载
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>权限名称</th>
                            <th>权限表达式</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${result.list}" var="entity" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>${entity.name}</td>
                                <td>${entity.expression}</td>
                                <td>
                                    <a href="/permission/delete.do?id=${entity.id}" class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页-->
                    <%@include file="/WEB-INF/views/common/page.jsp"%>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</div>
</body>
</html>
