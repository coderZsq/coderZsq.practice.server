<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    pageContext.setAttribute("num", 30);
%>

<c:choose>
    <c:when test="${num > 20}">num大于20</c:when>
    <c:when test="${num < 20}">num小于20</c:when>
    <c:otherwise>num等于20</c:otherwise>
</c:choose>

<hr>

<c:if test="${num > 20}">
    num > 20
</c:if>
<c:if test="${num > 20}" var="result" scope="page"/>
${result}

</body>
</html>
