<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Castie! 简历管理-登录</title>
    <%@ include file="../WEB-INF/page/admin/common/style.jsp"%>
</head>

<body class="login-page">
    <div class="login-box">
        <div class="logo">
            <a href="javascript:void(0);"><b>Castie! 简历管理</b></a>
            <small>您身边最好用的简历助手</small>
        </div>
        <div class="card">
            <div class="body">
                <form class="form-validation" method="post" action="${ctx}/user/login">
                    <div class="msg">赶紧登录吧</div>
                    <div class="input-group form-group form-float">
                        <span class="input-group-addon">
                            <i class="material-icons">email</i>
                        </span>
                        <div class="form-line">
                            <input type="email" class="form-control"
                                   value="a13701777868@sina.com"
                                   name="email" maxlength="50" placeholder="邮箱" required autofocus>
                        </div>
                    </div>
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                            <i class="material-icons">lock</i>
                        </span>
                        <div class="form-line">
                            <input type="hidden" name="password">
                            <input id="originPassword" type="password"
                                   value="666"
                                   class="form-control" maxlength="20"placeholder="密码" required>
                        </div>
                    </div>
                    <div class="input-group form-group captcha">
                        <span class="input-group-addon">
                            <i class="material-icons">security</i>
                        </span>
                        <div class="form-line">
                            <input type="text" class="form-control" name="captcha" placeholder="验证码" required>
                        </div>
                        <img id="captcha" src="${ctx}/user/captcha" alt="验证码">
                    </div>
                    <div class="row">
                        <div class="col-xs-8 p-t-5">
                            <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-pink">
                            <label for="rememberme">记住密码</label>
                        </div>
                        <div class="col-xs-4">
                            <button class="btn btn-block bg-pink waves-effect" type="submit">登录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="../WEB-INF/page/admin/common/script.jsp"%>
    <script src="${ctx}/asset/plugin/JavaScript-MD5/md5.min.js"></script>
    <script>
        addValidatorRules('.form-validation', function () {
            $('[name=password]').val(md5($('#originPassword').val()))
            // 先弹框
            swal({
                title: '正在登录中...',
                text: ' ',
                icon: 'info',
                buttons: false,
                closeOnClickOutside: false
            })

            // 利用AJAX发送请求给服务器
            $.post('${ctx}/user/login', {
                email: $('[name=email]').val(),
                password: $('[name=password]').val(),
                captcha: $('[name=captcha]').val()
            }, function (data) {
                if (data.success) {
                    location.href = '${ctx}/user/admin'
                } else {
                    swal({
                        title: '提示',
                        text: data.msg,
                        icon: 'error',
                        dangerMode: true,
                        buttons: false,
                        timer: 1500
                    })
                }
            }, 'json')
            return false
        })

        $('#captcha').click(function () {
            $(this).hide().attr('src', '${ctx}/user/captcha?time=' + new Date().getTime()).fadeIn()
        })
    </script>
</body>

</html>