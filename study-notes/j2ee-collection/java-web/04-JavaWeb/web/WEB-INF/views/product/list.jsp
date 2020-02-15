<%--
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
<div align="center">
    当前登录用户: [${sessionScope.USER_IN_SESSION.username}]
<%--    <a href="/logout">注销登录</a>--%>
    <a href="/login.jsp">注销登录</a>
</div>
<hr>
<a href="${pageContext.request.contextPath}/product?cmd=edit">添加货品</a>
<table border="1" width="90%" cellpadding="0" cellspacing="0">
    <tr style="background-color: orange">
        <th>货品编号</th>
        <th>货品名</th>
        <th>货品品牌</th>
        <th>供&nbsp;应&nbsp;商</th>
        <th>零&nbsp;售&nbsp;价</th>
        <th>成&nbsp;本&nbsp;价</th>
        <th>折&nbsp;&nbsp;扣</th>
        <th>操&nbsp;&nbsp;作</th>
    </tr>
    <c:forEach items="${products}" var="p" varStatus="vs">
        <tr style='background-color: ${vs.count % 2 == 0 ? "gray" : ""}'>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.brand}</td>
            <td>${p.supplier}</td>
            <td>${p.salePrice}</td>
            <td>${p.costPrice}</td>
            <td>${p.cutoff}</td>
            <td>
                <a href="${pageContext.request.contextPath}/product?cmd=delete&id=${p.id}">删除</a> |
                <a href="${pageContext.request.contextPath}/product?cmd=edit&id=${p.id}">编辑</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
