<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String name = request.getParameter("name");
%>
<%=name%>

<%--请求转发--%>
<jsp:forward page="/jspdemo/page.jsp">
    <jsp:param name="age" value="17"/>
</jsp:forward>
</body>
</html>
