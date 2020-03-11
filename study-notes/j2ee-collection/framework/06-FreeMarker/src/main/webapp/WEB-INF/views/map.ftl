<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#-- 现货区所有的key -->
<#-- ? 后面紧跟的是签名对象的方法, 相当于java中的方法调用 -->
<#assign keys=map?keys>
<#list keys as key>
    ${key} ---> ${map[key]} <br/>
</#list>
</body>
</html>