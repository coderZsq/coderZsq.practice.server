<%@ page import="java.util.UUID" %><%--
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
<%--<%--%>
<%--    // 生成一个随机数--%>
<%--    String token = UUID.randomUUID().toString();--%>
<%--    // 存放于session中, 将来用来做判断--%>
<%--    session.setAttribute("TOKEN_IN_SESSION", token);--%>
<%--%>--%>
<form action="/transform" method="post">
<%--    <input type="hidden" name="token" value="<%=token%>">--%>
    <input type="hidden" name="token" value="${token}">
    转账金额: <input type="text" name="money" required><br>
    <input type="submit" value="朕要转账">
</form>
</body>
</html>
