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
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage">
                    <div class="form-group">
                        <label for="keyword">关键字:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword" value=""
                               placeholder="请输入姓名/邮箱">
                    </div>
                    <div class="form-group">
                        <label for="dept"> 部门:</label>
                        <select class="form-control" id="dept" name="deptId">
                            <option value="-1">全部</option>
                            <#list depts as dept>
                                <option value="${(dept.id)!}">${(dept.name)!}</option>
                            </#list>
                        </select>
                        <script>
                            $("#dept option[value='-1']").prop("selected", true);
                        </script>
                    </div>
                    <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                        查询
                    </button>
                    <a href="javascript:;" class="btn btn-success btn_redirect" data-url="/employee/input.do">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>

                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>姓名</th>
                            <th>邮箱</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                        <#-- 遍历员工信息 -->
                        <#list emps as emp>
                            <tr>
                                <td>${(emp.id)!}</td>
                                <td>${(emp.name)!}</td>
                                <td>${(emp.email)!}</td>
                                <td>${(emp.age)!}</td>
                                <td>${(emp.dept.name)!}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input" href="/employee/input.do?id=${(emp.id)!}">
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="/employee/delete.do?id=${(emp.id)!}"
                                       class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->

                    <html>
                    <div style="text-align: center;">
                        <ul id="pagination" class="pagination"></ul>
                    </div>
                    <script>
                        //分页
                        $(function () {
                            $("#pagination").twbsPagination({
                                totalPages: 5 || 1,
                                startPage: 1 || 1,
                                visiblePages: 5,
                                first: "首页",
                                prev: "上页",
                                next: "下页",
                                last: "尾页",
                                initiateStartPageClick: false,
                                onPageClick: function (event, page) {
                                    $("#currentPage").val(page);
                                    $("#searchForm").submit();
                                }
                            });
                        })
                    </script>
                    </html>
                </div>
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
