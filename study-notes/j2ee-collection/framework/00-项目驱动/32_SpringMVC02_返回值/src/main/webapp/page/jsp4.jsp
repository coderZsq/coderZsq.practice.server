<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/9/14
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

This is jsp4.jsp

<div>
    name:${param.name}, price:<%=request.getParameter("price")%>
</div>

</body>
</html>
