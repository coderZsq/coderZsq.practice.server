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
    <script type="application/javascript">
        function go(pageNum) {
            if (pageNum) {
                // 把需要跳转的页面存储在表单中
                document.getElementById("currentPage").value = pageNum;
            }
            document.forms[0].submit();
        }
    </script>
</head>
<body>
<s:property value="#session.user_in_session"/>
<hr>

<s:form namespace="/" action="employee">
    名称: <s:textfield name="queryObject.name"/>
    工资: <s:textfield name="queryObject.minSalary"/>
    到: <s:textfield name="queryObject.maxSalary"/>
    <s:submit value="查询"/>

    <s:a namespace="/" action="employee_input">添加员工</s:a>
    <table border="1" cellpadding="0" cellspacing="0" width="90%">
        <tr>
            <th>员工编号</th>
            <th>员工名称</th>
            <th>员工密码</th>
            <th>员工工资</th>
            <th>操&emsp;&emsp;作</th>
        </tr>
        <s:iterator value="#pageResult.result">
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
        <tr align="center">
            <td colspan="5">
                <a href="javascript:go(<s:property value="1"/>);">首页</a>
                <a href="javascript:go(<s:property value="#pageResult.prevPage"/>);">上页</a>
                <a href="javascript:go(<s:property value="#pageResult.nextPage"/>);">下页</a>
                <a href="javascript:go(<s:property value="#pageResult.totalPage"/>);">末页</a>
                一共<s:property value="#pageResult.totalCount"/>条,
                当前第<s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.totalPage"/>页,
                跳转到<s:textfield name="queryObject.currentPage" id="currentPage" size="3"/>页
                <input type="button" value="GO" onclick="go();">
                每页显示
                    <s:select name="queryObject.pageSize" list="{3, 5, 10}" onchange="go(1);"/>
                条
            </td>
        </tr>
    </table>
</s:form>

</body>
</html>
