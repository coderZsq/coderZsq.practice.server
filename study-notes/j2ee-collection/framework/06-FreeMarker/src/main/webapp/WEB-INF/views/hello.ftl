<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#assign age=19>
<#assign score=85>
<h1>Welcome ${user}!</h1>
<p>最新产品推荐</p>
<a href="${url}">${name}</a>
获取变量值: ${age}
<#if score &gt; 90 >
    优秀
    <#elseif score &gt; 80>
    良好
    <#else >
    需要努力
</#if>
</body>
</html>