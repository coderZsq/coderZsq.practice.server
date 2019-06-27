<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-27
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>登录页面</h3>
<form action="/interceptor/login" method="post">
    <s:text name="username"/>: <input type="text" name="username"><br>
    <s:text name="password"/>: <input type="text" name="password"><br>
    <input type="submit" value="<s:text name="login"/>">
</form>
</body>
</html>
