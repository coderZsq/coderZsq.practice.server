<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("ctx", request.getContextPath()); %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>小码哥教育-Error</title>
    <link rel="icon" href="${ctx}/asset/admin/img/favicon.png" type="image/x-icon">
    <link href="${ctx}/asset/plugin/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/asset/plugin/node-waves/waves.css" rel="stylesheet">
    <link href="${ctx}/asset/plugin/animate-css/animate.min.css" rel="stylesheet">
    <link href="${ctx}/asset/admin/css/material-icons.css" rel="stylesheet">
    <link href="${ctx}/asset/admin/css/style.min.css" rel="stylesheet">
    <link href="${ctx}/asset/admin/css/main.css" rel="stylesheet">
</head>

<body class="five-zero-zero">
    <div class="five-zero-zero-container">
        <div class="error-title">喔豁，出错了</div>
        <div class="error-msg">${error}</div>
        <div class="button-place">
            <a href="#" class="btn btn-default btn-lg waves-effect">回到首页</a>
        </div>
    </div>
</body>

</html>