<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#list names as name>
    ${name} -->, --> ${name_index} <#if name_has_next>;</#if>
</#list>
</body>
</html>