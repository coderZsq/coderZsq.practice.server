<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>编辑客户</title>
</head>
<body>

<form action="/crm/customer/update" method="post">
    <%--隐藏域--%>
    <input type="hidden" name="id" value="${customer.id}">
    <div>姓名<input type="text" name="name" value="${customer.name}"></div>
    <div>年龄<input type="text" name="realAge" value="${customer.realAge}"></div>
    <div>身高<input type="text" name="height" value="${customer.height}"></div>
    <div><button type="submit">更新</button></div>
</form>

</body>
</html>