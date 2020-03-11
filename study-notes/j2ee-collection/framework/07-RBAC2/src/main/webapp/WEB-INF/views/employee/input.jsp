<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp" %>

    <script>
        function moveSelected(sourceClass, targetClass) {
            //将sourceClass 中被选中的option添加到targetClass
            $("." + sourceClass + ">option:selected").appendTo($("." + targetClass));
        }

        function moveAll(sourceClass, targetClass) {
            //将sourceClass 中被选中的option添加到targetClass
            $("." + sourceClass + ">option").appendTo($("." + targetClass));
        }

        $(function () {
            //1.当admin改变的时候 获取 admin的checked的值
            //判断admin是否被选中
            //选中把id为role的div删除,保留事件,等待下一次分配角色
            // detach并返回删除的事件
            //如果没有选中在admin所在的div后面插入之前被删除的role的事件
            var roleDiv;
            $("#admin").change(function () {
                //获取自己的checked状态
                if (this.checked) {
                    //判断如果是true,删除id为role的div,保留删除的事件
                    roleDiv = $("#role").detach();
                } else {
                    //如果是false,在admin后面添加div
                    $("#adminDiv").after(roleDiv);
                }
            });

            <c:if test="${entity.admin}">
            roleDiv = $("#role").detach();
            </c:if>
        });

        $(function () {
            //角色去重
            //定义一个数组存储role的id
            var roleIds= [];
            //获取selfRoles中的id添加到数组中
            $.each($(".selfRoles > option"),function (index, option) {
                roleIds.push($(option).val());
            });
            //遍历左边的option
            $.each($(".allRoles > option"),function (index, option) {
                if($.inArray($(option).val(),roleIds)>=0){//判断值是否存在在数组中,在返回索引,不在返回-1
                    $(option).remove();
                }
            });

            //表单提交
            $("#submitBtn").click(function () {
                $(".selfRoles option").prop("selected",true);
                $("#editForm").submit();
            });
        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${entity.id}" name="id">

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>

                    <c:if test="${empty entity}"><%--空就有密码--%>
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
                    </c:if>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" value="${entity.email}"
                                   placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${entity.age}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <c:forEach items="${depts}" var="dept">
                                    <option value="${dept.id}">${dept.name}</option>
                                </c:forEach>
                            </select>
                            <script>
                                $(function () {
                                    $("#dept").val(${entity.dept.id});
                                });
                            </script>
                        </div>
                    </div>
                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox">
                            <script>
                                $(function () {
                                    $("#admin").prop("checked",${entity.admin});
                                });
                            </script>
                        </div>
                    </div>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="roleIds">
                                    <c:forEach items="${entity.roles}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
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
    <%@include file="/WEB-INF/views/common/footer.jsp" %>
</div>


</body>
</html>
