<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-24
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    Index

  <%
    session.setAttribute("name", "唐陌");
    session.setAttribute("age", "23");
    session.removeAttribute("age");
    session.setAttribute("name", "白若遥");
  %>

  </body>
</html>
