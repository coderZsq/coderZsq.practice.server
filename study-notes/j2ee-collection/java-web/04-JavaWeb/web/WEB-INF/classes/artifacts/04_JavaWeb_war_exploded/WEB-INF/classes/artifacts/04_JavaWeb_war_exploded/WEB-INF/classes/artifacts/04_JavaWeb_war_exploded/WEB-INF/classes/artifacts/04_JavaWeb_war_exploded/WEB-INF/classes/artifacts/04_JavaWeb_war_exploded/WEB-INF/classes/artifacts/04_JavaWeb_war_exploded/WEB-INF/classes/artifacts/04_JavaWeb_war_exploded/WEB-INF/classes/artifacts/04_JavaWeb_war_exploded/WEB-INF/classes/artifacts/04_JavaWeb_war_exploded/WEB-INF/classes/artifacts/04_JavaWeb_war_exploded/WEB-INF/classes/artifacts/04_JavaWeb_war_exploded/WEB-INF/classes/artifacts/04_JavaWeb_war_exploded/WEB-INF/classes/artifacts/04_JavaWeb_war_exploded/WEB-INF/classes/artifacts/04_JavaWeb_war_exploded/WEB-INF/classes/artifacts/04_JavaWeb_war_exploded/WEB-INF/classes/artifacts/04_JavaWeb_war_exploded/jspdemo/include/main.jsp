<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="top.jsp"%>
<br>
main代码
<br>
<%@include file="foot.jsp"%>

<jsp:include page="top.jsp">
    <jsp:param name="" value=""/>
</jsp:include>
<br>
main代码
<br>
<jsp:include page="foot.jsp"/>
</body>
</html>
