<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/8/3
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<% pageContext.setAttribute("name", "sq"); %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        // ServletContext
        System.out.println(application);
        // 代表当前页面 (当前的Servlet)
        System.out.println(page);
        // 当前页面共享数据
        System.out.println(pageContext);
        // 用来输出内容
        System.out.println(out);
        System.out.println(config);
        System.out.println(exception);
    %>

    <!--
    EL表达式中
    pageScope -> pageContext
    requestScope -> request
    sessionScope -> session
    applicationScope -> application
    param 可以获取请求参数
    -->
    <input type="text" value="${param.age}">
</body>
</html>
