<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 5:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--
    需求1: 从1输出到10
    1 2 3 4 5 6 7 8 9 10
 --%>
<c:forEach var="num" begin="1" end="10" step="1">
    ${num}
</c:forEach>

<%--
    需求2: 迭代一个集合中所有的数据
 --%>
<%
    pageContext.setAttribute("list", java.util.Arrays.asList("A", "B", "C", "D"));
%>
<c:forEach items="${list}" var="ele">
    ${ele}<br>
</c:forEach>

</body>
</html>
