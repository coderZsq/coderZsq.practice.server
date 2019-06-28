<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-28
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    request.setAttribute("info", "<strong>Castie!</strong>");
%>

<s:property value="#request.info" default="没有取到的默认值" escapeHtml="true"/><br>
<s:property value="#request.info" default="没有取到的默认值" escapeHtml="false"/><br>

<%--<a href="/down/download?fileName=地球上线.txt">地球上线.txt</a><br>--%>
<s:a namespace="/down" action="download">
    <s:param name="fileName" value='%{"地球上线.txt"}'/>
    地球上线.txt
</s:a>
<br>
<s:url namespace="/down" action="download" value="myUrl">
    <s:param name="fileName" value='%{"地球上线.txt"}'/>
</s:url>
<s:a href="#myUrl">
    地球上线
</s:a>

</body>
</html>
