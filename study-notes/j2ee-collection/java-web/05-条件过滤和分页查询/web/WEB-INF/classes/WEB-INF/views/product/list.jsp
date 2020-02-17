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
<table border="1" width="90%" cellpadding="0" cellspacing="0">
    <tr style="background-color: orange">
        <th>货品编号</th>
        <th>货品名</th>
        <th>货品品牌</th>
        <th>货品分类</th>
        <th>供&nbsp;应&nbsp;商</th>
        <th>零&nbsp;售&nbsp;价</th>
        <th>成&nbsp;本&nbsp;价</th>
        <th>折&nbsp;&nbsp;扣</th>
    </tr>
    <c:forEach items="${products}" var="p" varStatus="vs">
        <tr style='background-color: ${vs.count % 2 == 0 ? "gray" : ""}'>
            <td>${p.id}</td>
            <td>${p.productName}</td>
            <td>${p.brand}</td>
            <td>
                <c:choose>
                    <c:when test="${p.dir_id == 1}">鼠标</c:when>
                    <c:when test="${p.dir_id == 2}">无线鼠标</c:when>
                    <c:when test="${p.dir_id == 3}">有线鼠标</c:when>
                    <c:when test="${p.dir_id == 4}">游戏鼠标</c:when>
                </c:choose>
            </td>
            <td>${p.supplier}</td>
            <td>${p.salePrice}</td>
            <td>${p.costPrice}</td>
            <td>${p.cutoff}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
