<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-26
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>登录页面</h3>
<form action="/interceptor/login" method="post">
    账号: <input type="text" name="username"><br>
    密码: <input type="text" name="password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
