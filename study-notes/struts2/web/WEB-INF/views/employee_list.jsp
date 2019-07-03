<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-07-03
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<s:form namespace="/" action="employee">
    名称: <s:textfield name="queryObject.name"/>
    工资: <s:textfield name="queryObject.minSalary"/>
    到: <s:textfield name="queryObject.maxSalary"/>
    <s:submit value="查询"/>
</s:form>

<s:a namespace="/" action="employee_input">添加员工</s:a>
<table border="1" cellpadding="0" cellspacing="0" width="90%">
    <tr>
        <th>员工编号</th>
        <th>员工名称</th>
        <th>员工密码</th>
        <th>员工工资</th>
        <th>操&emsp;&emsp;作</th>
    </tr>
    <s:iterator value="#employees">
        <tr>
            <td><s:property value="id"/></td>
            <td><s:property value="name"/></td>
            <td><s:property value="password"/></td>
            <td><s:property value="salary"/></td>
            <td>
                <s:url var="inputUrl" namespace="/" action="employee_input">
                    <s:param name="employee.id" value="id"/>
                </s:url>
                <a href="<s:property value="#inputUrl"/>">编辑</a>
                <s:a namespace="/" action="employee_delete"><s:param name="employee.id" value="id"/>删除</s:a>
            </td>
        </tr>
    </s:iterator>
</table>

</body>
</html>
