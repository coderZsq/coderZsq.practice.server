<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/15
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>购物车列表</h3>
<table cellpadding="0" cellspacing="0" width="500" border="1">
    <tr>
        <td>商品名称</td>
        <td>零售价格</td>
        <td>购买数量</td>
        <td>操作</td>
    </tr>
    <c:forEach items="${SHOPPINGCART_IN_SESSION.items}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td>${item.num}</td>
            <td><a href="/shoppingcart?cmd=delete&id=${item.id}">删除</a></td>
        </tr>
    </c:forEach>
    <tr align="right">
        <td colspan="4">购物车总价: ${SHOPPINGCART_IN_SESSION.totalPrice}</td>
    </tr>
</table>
<a href="/shoppingcart/product_list.jsp">去购物</a>
</body>
</html>
