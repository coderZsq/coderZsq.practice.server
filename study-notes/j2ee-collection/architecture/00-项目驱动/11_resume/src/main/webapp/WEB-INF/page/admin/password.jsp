<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>小码哥简历管理-修改密码</title>
    <%@ include file="common/head.jsp" %>
</head>

<body class="theme-blue">

<%@ include file="common/middle.jsp" %>

<section class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="header">
                        <h2>修改密码</h2>
                    </div>
                    <div class="body">

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<%@ include file="common/foot.jsp"%>

<script>
    $('.menu .list .password').addClass('active')
    addValidatorRules('.form-validation')

</script>
</body>

</html>
