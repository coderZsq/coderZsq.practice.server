<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 5:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    pageContext.setAttribute("date", new java.util.Date());
%>
北京时间: ${date}<br>
<fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm:ss"/>

</body>
</html>
