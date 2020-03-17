<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common/link.ftl">
    <script>
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="${(employee.id)!''}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(employee.name)!''}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>
                    <#if !employee??>
                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="repassword" class="col-sm-2 control-label">验证密码：</label>
                            <div class="col-sm-6">
                                <input type="password" class="form-control" id="repassword" name="repassword"
                                       placeholder="再输入一遍密码">
                            </div>
                        </div>
                    </#if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email"
                                   value="${(employee.email)!''}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${(employee.age)!''}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <#list depts as d>
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                            <script>
                                $("#dept option[value='${(employee.dept.id)!}']").prop("selected", true);
                            </script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox">
                            <#if employee?? && employee.admin >
                                <script>
                                    $("#admin").prop("checked", true);
                                </script>
                            </#if>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="button" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>

</body>
</html>
