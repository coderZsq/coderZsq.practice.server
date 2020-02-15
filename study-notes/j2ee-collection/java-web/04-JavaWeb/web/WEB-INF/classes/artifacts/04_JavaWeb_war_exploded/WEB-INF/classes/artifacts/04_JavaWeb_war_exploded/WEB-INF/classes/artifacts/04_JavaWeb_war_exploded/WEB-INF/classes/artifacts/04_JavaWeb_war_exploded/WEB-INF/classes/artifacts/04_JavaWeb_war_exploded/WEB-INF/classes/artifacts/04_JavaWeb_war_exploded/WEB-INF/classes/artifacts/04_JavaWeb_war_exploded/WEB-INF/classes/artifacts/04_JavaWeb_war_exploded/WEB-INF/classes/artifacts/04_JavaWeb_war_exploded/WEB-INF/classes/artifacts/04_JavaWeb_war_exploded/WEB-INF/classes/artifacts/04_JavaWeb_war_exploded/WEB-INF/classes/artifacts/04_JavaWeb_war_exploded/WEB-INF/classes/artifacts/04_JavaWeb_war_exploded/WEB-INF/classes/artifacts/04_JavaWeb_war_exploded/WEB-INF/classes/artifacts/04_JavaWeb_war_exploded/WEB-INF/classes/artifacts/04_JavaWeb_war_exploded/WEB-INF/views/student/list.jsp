<%@ page import="com.coderZsq._17_smis.domain.Student" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/student?cmd=edit">添加学生</a>
<table border="1" width="70%" cellpadding="0" cellspacing="0">
    <tr style="background-color: orange">
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${students}" var="s" varStatus="vs">
        <tr style='background-color: ${vs.count % 2 == 0 ? "gray" : ""}'>
            <td>${s.id}</td>
            <td>${s.name}</td>
            <td>${s.age}</td>
            <td>
                <a href="${pageContext.request.contextPath}/student?cmd=delete&id=${s.id}">删除</a> |
                <a href="${pageContext.request.contextPath}/student?cmd=edit&id=${s.id}">编辑</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
