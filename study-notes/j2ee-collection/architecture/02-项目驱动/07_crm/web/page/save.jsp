<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <c:choose>
        <c:when test="${empty customer}">
            <title>添加客户</title>
        </c:when>
        <c:otherwise>
            <title>编辑客户</title>
        </c:otherwise>
    </c:choose>
    <%--
    <c:if test="${empty customer}">
        <title>添加客户</title>
    </c:if>
    <c:if test="${not empty customer}">
        <title>编辑客户</title>
    </c:if>
    --%>
</head>
<body>

<form action="/crm/customer/save" method="post">
    <c:if test="${not empty customer}">
        <%--隐藏域--%>
        <input type="hidden" name="id" value="${customer.id}">
    </c:if>
    <div>姓名<input type="text" name="name" value="${customer.name}"></div>
    <div>年龄<input type="text" name="realAge" value="${customer.realAge}"></div>
    <div>身高<input type="text" name="height" value="${customer.height}"></div>
    <div>
        <button type="submit">
            <c:choose>
                <c:when test="${empty customer}">添加</c:when>
                <c:otherwise>更新</c:otherwise>
            </c:choose>
        </button>
    </div>
</form>

</body>
</html>