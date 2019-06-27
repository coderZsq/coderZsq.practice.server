<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-27
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>文件上传</h3>
<s:form namespace="/upload" action="upload" method="POST" enctype="multipart/form-data" theme="xhtml">
    <s:textfield label="名称" name="username"/>
    <s:file label="头像" name="headImg"/>
    <s:submit value="提交"/>
</s:form>

</body>
</html>
