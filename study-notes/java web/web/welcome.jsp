<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-25
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

${sessionScope.USER_IN_SESSION}
<%--<%--%>
    <%--Object user = session.getAttribute("USER_IN_SESSION");--%>
    <%--if (user == null) {--%>
        <%--response.sendRedirect("/login.jsp");--%>
    <%--}--%>
<%--%>--%>
<hr>
<a href="/function1.jsp">功能1</a><br>
<a href="/function2.jsp">功能2</a><br>
<a href="/function3.jsp">功能3</a><br>

</body>
</html>
