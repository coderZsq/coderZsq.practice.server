<html>
<head>
    <title>Welcome!</title>
</head>
<body>
访问员工信息<br>
${(emp.password)!"默认值"}
<#if (emp.admin)??>
    123
</#if>
</body>
</html>