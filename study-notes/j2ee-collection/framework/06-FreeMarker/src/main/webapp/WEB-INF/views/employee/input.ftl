<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>

    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/js/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/js/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/js/adminlte/css/fonts.googleapis.com.css">

    <script src="/js/jquery/jquery.min.js"></script>
    <script src="/js/bootstrap/js/bootstrap.js"></script>
    <script src="/js/adminlte/js/adminlte.min.js"></script>
    <script src="/js/plugins/twbsPagination/jquery.twbsPagination.min.js"></script>
    <script src="/js/system/commonAll.js"></script>


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


            roleDiv = $("#role").detach();

        });

        $(function () {
            //角色去重
            //定义一个数组存储role的id
            var roleIds = [];
            //获取selfRoles中的id添加到数组中
            $.each($(".selfRoles > option"), function (index, option) {
                roleIds.push($(option).val());
            });
            //遍历左边的option
            $.each($(".allRoles > option"), function (index, option) {
                if ($.inArray($(option).val(), roleIds) >= 0) {//判断值是否存在在数组中,在返回索引,不在返回-1
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

    <header class="main-header">
        <a href="../../index2.html" class="logo">
            <span class="logo-mini">CRM</span>
            <span class="logo-lg"><b>叩丁狼客户管理系统</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/js/adminlte/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs"></span>
                        </a>
                        <ul class="dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close"
                            style="min-width: 100px;">
                            <li>
                                <a href="#">
                                    <i class="fa fa-cog"></i>
                                    个人设置
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html">
                                    <i class=" fa fa-user"></i>
                                    个人信息
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="/logout.do">
                                    <i class="fa fa-power-off"></i>
                                    注销
                                </a>
                            </li>
                        </ul>
                    </li>

                </ul>
            </div>
        </nav>
    </header>
    <!--菜单回显-->


    <aside class="main-sidebar">
        <section class="sidebar">
            <ul class="sidebar-menu" data-widget="tree">
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>系统管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                    </a>
                    <ul class="treeview-menu">
                        <li name="department"><a href="/department/list.do"><i class="fa fa-circle-o"></i> 部门管理</a></li>
                        <li name="employee"><a href="/employee/list.do"><i class="fa fa-circle-o"></i> 员工管理</a></li>
                        <li name="permission"><a href="/permission/list.do"><i class="fa fa-circle-o"></i> 权限管理</a></li>
                        <li name="role"><a href="/role/list.do"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-files-o"></i>
                        <span>数据管理</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li name="systemDictionary"><a href="/systemDictionary/list.do"><i class="fa fa-circle-o"></i>
                                字典目录</a>
                        </li>
                        <li name="systemDictionaryItem"><a href="/systemDictionaryItem/list.do"><i
                                        class="fa fa-circle-o"></i> 字典明细</a></li>
                    </ul>
                </li>

                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i>
                        <span>客户管理</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li name="customer"><a href="/customer/list.do"><i class="fa fa-circle-o"></i> 客户列表</a></li>
                        <li name="customer_potential"><a href="/customer/potentialList.do"><i
                                        class="fa fa-circle-o"></i>
                                潜在客户</a></li>
                        <li name="customer_pool"><a href="/customer/poolList.do"><i class="fa fa-circle-o"></i> 客户池</a>
                        </li>
                        <li name="customer_fail"><a href="/customer/failList.do"><i class="fa fa-circle-o"></i> 失败客户</a>
                        </li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 正式客户</a></li>
                        <li><a href="#"><i class="fa fa-circle-o"></i> 流失客户</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-edit"></i>
                        <span>客户历史</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">

                        <li name="customerTraceHistory"><a href="/customerTraceHistory/list.do"><i
                                        class="fa fa-circle-o"></i> 跟进历史</a></li>
                        <li name="customerTransfer"><a href="/customerTransfer/list.do"><i class="fa fa-circle-o"></i>
                                移交历史</a>
                        </li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-laptop"></i>
                        <span>报表统计</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li name="customerReport"><a href="/customerReport/list.do"><i class="fa fa-circle-o"></i>潜在客户报表</a>
                        </li>
                    </ul>
                </li>

            </ul>
        </section>
    </aside>

    <script>
        $(function () {
            //菜单初始
            $('.sidebar-menu').tree();
            //菜单激活
            var cuLi = $(".treeview-menu li[name='employee']");
            cuLi.addClass("active");
            cuLi.closest(".treeview").addClass("active")
        })
    </script>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(emp.id)!}" name="id">

                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${(emp.name)!}"
                                   placeholder="请输入用户名">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" value="${(emp.email)!}"
                                   placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${(emp.age)!}"
                                   placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <#list depts as dept>
                                    <option value="${(dept.id)!}">${(dept.name)!}</option>
                                </#list>
                            </select>
                            <script>
                                $(function () {
                                    $("#dept").val(${(emp.dept.id)!});
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
                                    $("#admin").prop("checked", ${(emp.admin)?string("true", "false")!"false"});
                                });
                            </script>
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

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2016-2019 <a href="http://www.wolfcode.cn">叩丁狼</a>.</strong> All rights
        reserved.
    </footer>
</div>
</body>
</html>
