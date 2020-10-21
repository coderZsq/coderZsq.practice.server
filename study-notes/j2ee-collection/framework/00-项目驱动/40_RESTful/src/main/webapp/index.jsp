<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/9/7
  Time: 11:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("ctx", request.getContextPath()); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
This is index.jsp
<form action="${ctx}/skills/10" method="get">
    <%--<input type="hidden" name="_method" value="delete">--%>
    <button type="submit">提交</button>
</form>
</body>
</html>
