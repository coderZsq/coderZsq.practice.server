<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 2:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
北京时间: <%=new java.util.Date().toLocaleString()%>
<!-- HTML注释 -->
<%-- JSP注释: 不会翻译到Servlet中 --%>

<%
    // Java代码
    String name = "coderZsq";
    boolean man = true;
%>
<%=name%>
<%!
    private String mXx;
    public void doWork() {}
%>

<%
    int num = 10;
    if (num > 5) {
%>
num大于5
<%
    } else if (num < 5) {
%>
num小于5
<%
    } else {
%>
num等于5
<%
    }
%>

</body>
</html>
