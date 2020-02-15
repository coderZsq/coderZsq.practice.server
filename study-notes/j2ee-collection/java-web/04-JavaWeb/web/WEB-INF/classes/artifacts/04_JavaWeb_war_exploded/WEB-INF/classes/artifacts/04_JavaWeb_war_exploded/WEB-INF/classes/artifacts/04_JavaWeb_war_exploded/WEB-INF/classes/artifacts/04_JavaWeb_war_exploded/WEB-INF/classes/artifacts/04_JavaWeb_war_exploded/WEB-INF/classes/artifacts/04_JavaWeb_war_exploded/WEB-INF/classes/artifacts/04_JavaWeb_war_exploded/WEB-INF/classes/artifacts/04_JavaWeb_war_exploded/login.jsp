<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/15
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>用户登录</h3>
<span style="color: red">${errorMsg}</span>
<form action="/login" method="post">
    账户: <input type="text" name="username" required><br>
    密码: <input type="password" name="password" required><br>
    <input type="submit" value="朕要登录">
</form>
</body>
</html>
