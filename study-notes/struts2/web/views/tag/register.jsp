<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2019-06-28
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title>Title</title>
    <style>
        .myClass {
            background-color: yellow;
        }
    </style>
</head>
<body>

<h3>注册界面</h3>
<s:form namespace="" action="" method="POST" theme="simple">
    <s:hidden name="id" value="1234"/>

    账号: <s:textfield name="username" cssClass="myClass"/><br>
    密码: <s:password name="password"/><br>
    <%--OGNL 构建List对象: {'A', 'B', 'C'}--%>
    <%--OGNL 构建Map对象: #{'key': 'value1'}--%>
    性别: <s:radio name="gender" list="{'男', '女', '保密'}" value="%{'男'}"/><br>
    性别: <s:radio name="gender" list="#{'1': '男', '2': '女', '3': '保密'}" value="%{'男'}"/><br>
    爱好: <s:checkboxlist name="hobbys" list="#{'java':'Java语言', 'girl' : '美女', 'music' : '音乐'}"/><br>
    头像: <s:file name="headImg"/><br>
    介绍: <s:textarea name="intro" rows="4" cols="30"/><br>
    城市: <s:select list="#{'bj': '北京', 'sh': '上海', 'gz': '广州'}" headerKey="-1" headerValue="---请选择---"/><br>

    <s:submit value="注册"/>
    <s:reset value="重置"/>
</s:form>

</body>
</html>
