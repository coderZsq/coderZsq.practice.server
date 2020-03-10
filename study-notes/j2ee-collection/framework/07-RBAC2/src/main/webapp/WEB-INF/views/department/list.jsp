<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp" %>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="department"></c:set>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <a href="/department/input.do" class="btn btn-success btn-input" style="margin: 10px">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${result.list}" var="entity" varStatus="vs">
                            <tr>
                                <td>${vs.count}</td>
                                <td>${entity.name}</td>
                                <td>${entity.sn}</td>
                                <td>
                                        <a class="btn btn-info btn-xs btn-input"
                                           href="/department/input.do?id=${entity.id}">
                                            <span class="glyphicon glyphicon-pencil"></span> 编辑
                                        </a>
                                    <shiro:hasPermission name="department:delete">
                                        <a href="/department/delete.do?id=${entity.id}"
                                           class="btn btn-danger btn-xs btn-delete">
                                            <span class="glyphicon glyphicon-trash"></span> 删除
                                        </a>
                                    </shiro:hasPermission>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页-->
                    <%@include file="/WEB-INF/views/common/page.jsp" %>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>
