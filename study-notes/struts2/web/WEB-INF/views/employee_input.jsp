<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-07-03
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<s:form namespace="/" action="employee_saveOrUpdate" method="POST" theme="simple">
    <s:hidden name="employee.id"/>
    <table border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td>员工名称</td>
            <td><s:textfield name="employee.name"/></td>
        </tr>
        <s:if test="">
            <tr>
                <td>员工密码</td>
                <td><s:password name="employee.password" showPassword="true"/></td>
            </tr>
        </s:if>
        <tr>
            <td>员工工资</td>
            <td><s:textfield name="employee.salary"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="保存"></td>
        </tr>
    </table>
</s:form>

</body>
</html>
