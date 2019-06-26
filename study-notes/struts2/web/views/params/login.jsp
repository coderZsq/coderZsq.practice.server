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
<h3>第一种: Action本身作为Model对象, 通过setter方法封装(属性注入)</h3>
<form action="/params/login1" method="post">
    账号: <input type="text" name="username"><br>
    密码: <input type="text" name="password"><br>
    <input type="submit" value="登录">
</form>
<h3>第二种: 创建独立Model对象, 页面通过ognl表达式封装(属性注入)</h3>
<form action="/params/login2" method="post">
    账号: <input type="text" name="user.username"><br>
    密码: <input type="text" name="user.password"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
