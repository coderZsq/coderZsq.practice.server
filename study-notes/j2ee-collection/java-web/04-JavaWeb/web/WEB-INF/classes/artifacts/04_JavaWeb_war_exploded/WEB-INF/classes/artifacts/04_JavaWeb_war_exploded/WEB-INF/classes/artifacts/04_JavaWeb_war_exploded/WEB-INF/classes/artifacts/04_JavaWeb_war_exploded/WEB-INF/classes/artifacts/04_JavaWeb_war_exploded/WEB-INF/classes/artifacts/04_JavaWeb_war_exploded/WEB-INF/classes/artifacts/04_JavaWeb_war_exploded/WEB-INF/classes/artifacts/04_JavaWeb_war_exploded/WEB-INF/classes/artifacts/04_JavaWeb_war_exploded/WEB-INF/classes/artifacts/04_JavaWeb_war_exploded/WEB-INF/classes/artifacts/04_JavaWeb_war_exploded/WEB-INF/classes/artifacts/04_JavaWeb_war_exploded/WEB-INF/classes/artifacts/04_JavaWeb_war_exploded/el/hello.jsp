<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/11
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

获取上下文路径: <%=request.getContextPath()%>
<%--获取上下文路径: ${pageContext.getRequest()..getContextPath()}--%>
获取上下文路径: ${pageContext.request.contextPath}
<br>
\${1 + 3 / 2}: ${1 + 3 / 2}
<br>
<%
    // 向当前page作用域设置共享数据
    pageContext.setAttribute("list", null);
    pageContext.setAttribute("list", new java.util.ArrayList<>());
%>
${empty list}
${not empty list}
${!empty list}

<hr>
<%
    // JSP的四大作用域
    pageContext.setAttribute("msg", "pageContextValue");
    request.setAttribute("msg", "requestValue");
    session.setAttribute("msg", "sessionValue");
    application.setAttribute("msg", "applicationValue");
%>

<h3>使用EL获取不同作用域的属性值</h3>
pageContext:${pageScope.msg}<br>
request:${requestScope.msg}<br>
session:${sessionScope.msg}<br>
application:${applicationScope.msg}<br>

<h3>获取出每一个作用域中的数据</h3>
pageContext:<%=pageContext.getAttribute("msg")%><br>
request:<%=request.getAttribute("msg")%><br>
session:<%=session.getAttribute("msg")%><br>
application:<%=application.getAttribute("msg")%><br>
<hr>
<h3>pageContext的findAttribute方法</h3>
<%=pageContext.findAttribute("msg")%><br>
<%=pageContext.findAttribute("msg") == null ? "" : pageContext.findAttribute("msg")%><br>
${msg}
</body>
</html>
