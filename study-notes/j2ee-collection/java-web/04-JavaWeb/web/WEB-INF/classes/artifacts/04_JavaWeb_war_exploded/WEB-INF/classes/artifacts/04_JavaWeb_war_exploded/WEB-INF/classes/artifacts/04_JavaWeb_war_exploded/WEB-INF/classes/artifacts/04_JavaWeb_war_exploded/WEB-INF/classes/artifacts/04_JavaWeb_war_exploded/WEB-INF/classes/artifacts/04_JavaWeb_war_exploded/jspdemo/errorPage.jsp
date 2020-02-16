<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/2/10
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isErrorPage="true"
%>
<%
    String msg = exception.getMessage();
%>
错误信息: <%=msg%>