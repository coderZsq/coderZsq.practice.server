<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
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
            //角色去重
            //定义一个数组存储role的id
            var permissionIds= [];
            //获取selfRoles中的id添加到数组中
            $.each($(".selfPermissions > option"),function (index, option) {
                permissionIds.push($(option).val());
            });
            //遍历左边的option
            $.each($(".allPermissions > option"),function (index, option) {
                if($.inArray($(option).val(),permissionIds)>=0){//判断值是否存在在数组中,在返回索引,不在返回-1
                    $(option).remove();
                }
            });

            //表单提交
            $("#submitBtn").click(function () {
                $(".selfRoles option").prop("selected", true);
                $("#editForm").submit();
            });
        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="role"/>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${entity.id}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                                   placeholder="请输入角色名">
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="sn" class="col-sm-2 control-label">编码：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="sn" name="sn" value="${entity.sn}"
                                   placeholder="请输入角色编码">
                        </div>
                    </div>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple="" class="form-control allPermissions" size="15">
                                    <c:forEach items="${permissions}" var="permission">
                                        <option value="${permission.id}">${permission.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <select multiple="" class="form-control selfPermissions" size="15" name="permissionIds">
                                    <c:forEach items="${entity.permissions}" var="permission">
                                        <option value="${permission.id}">${permission.name}</option>
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
