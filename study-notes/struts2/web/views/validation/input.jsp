<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-27
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--使用 struts2标签获取校验失败信息--%>
<%--<s:fielderror fieldName="username"/>--%>

<s:debug/>

<s:form namespace="/validation" action="employee_save" method="post" theme="simple">
    账号: <s:textfield name="username"/><s:property value="fieldErrors.username[0]"/><br>
    密码: <s:password name="password"/><s:property value="fieldErrors.password[0]"/><br>
    <s:submit value="保存员工"/>
</s:form>

</body>
</html>
