<#assign ctx="${springMacroRequestContext.getContextPath()}">

<!DOCTYPE html>
<html dir="ltr" lang="zh">
<head>
    <meta charset="UTF-8">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${ctx}/assets/imgs/favicon.png">
    <title>数据字典类型</title>
    <link href="${ctx}/assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${ctx}/assets/css/style.css" rel="stylesheet">
    <link href="${ctx}/assets/css/main.css" rel="stylesheet">
</head>
<body>
<!-- ============================================================== -->
<!-- Preloader - style you can find in spinners.css -->
<!-- ============================================================== -->
<div class="preloader">
    <div class="lds-ripple">
        <div class="lds-pos"></div>
        <div class="lds-pos"></div>
    </div>
</div>
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <header class="topbar">
        <nav class="navbar top-navbar navbar-expand-md navbar-dark">
            <div class="navbar-header">
                <!-- This is for the sidebar toggle which is visible on mobile only -->
                <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                        class="ti-menu ti-close"></i></a>
                <!-- ============================================================== -->
                <!-- Logo -->
                <!-- ============================================================== -->
                <a class="navbar-brand" href="#">
                    <!-- Logo icon -->
                    <b class="logo-icon">
                        <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                        <!-- Dark Logo icon -->
                        <img src="${ctx}/assets/imgs/logo-icon.png" alt="homepage" class="dark-logo"/>
                        <!-- Light Logo icon -->
                        <img src="${ctx}/assets/imgs/logo-light-icon.png" alt="homepage" class="light-logo"/>
                    </b>
                    <!--End Logo icon -->
                    <!-- Logo text -->
                    <span class="logo-text">
                            <!-- dark Logo text -->
                            <img src="${ctx}/assets/imgs/logo-text.png" alt="homepage" class="dark-logo"/>
                        <!-- Light Logo text -->
                            <img src="${ctx}/assets/imgs/logo-light-text.png" class="light-logo" alt="homepage"/>
                        </span>
                </a>
                <!-- ============================================================== -->
                <!-- End Logo -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Toggle which is visible on mobile only -->
                <!-- ============================================================== -->
                <a class="topbartoggler d-block d-md-none waves-effect waves-light" href="javascript:void(0)"
                   data-toggle="collapse" data-target="#navbarSupportedContent">
                    <i class="ti-more"></i>
                </a>
            </div>
            <!-- ============================================================== -->
            <!-- End Logo -->
            <!-- ============================================================== -->
            <div class="navbar-collapse collapse" id="navbarSupportedContent">
                <!-- ============================================================== -->
                <!-- toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav mr-auto float-left">
                    <!-- This is  -->
                    <li class="nav-item"><a
                            class="nav-link sidebartoggler d-none d-md-block waves-effect waves-dark"
                            href="javascript:void(0)"><i class="ti-menu"></i></a></li>
                    <!-- ============================================================== -->
                    <!-- Search -->
                    <!-- ============================================================== -->
                    <li class="nav-item d-none d-md-block search-box"><a
                            class="nav-link d-none d-md-block waves-effect waves-dark" href="javascript:void(0)"><i
                            class="ti-search"></i></a>
                        <form class="app-search">
                            <input type="text" class="form-control" placeholder="Search & enter">
                            <a class="srh-btn"><i class="ti-close"></i></a>
                        </form>
                    </li>
                </ul>
                <!-- ============================================================== -->
                <!-- Right side toggle and nav items -->
                <!-- ============================================================== -->
                <ul class="navbar-nav float-right">
                    <!-- ============================================================== -->
                    <!-- Profile -->
                    <!-- ============================================================== -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle waves-effect waves-dark" href="" data-toggle="dropdown">
                            <img src="${ctx}/assets/imgs/users/2.jpg" alt="user" width="30" class="profile-pic rounded-circle">
                        </a>
                        <div class="dropdown-menu mailbox dropdown-menu-right">
                            <ul class="dropdown-user list-style-none">
                                <li>
                                    <div class="dw-user-box p-3 d-flex">
                                        <div class="u-img"><img src="${ctx}/assets/imgs/users/2.jpg" alt="user"
                                                                class="rounded" width="80"></div>
                                        <div class="u-text ml-2">
                                            <h4 class="mb-0">Steave Jobs</h4>
                                            <p class="text-muted mb-1 font-14">varun@gmail.com</p>
                                            <a href="#"
                                               class="btn btn-rounded btn-danger btn-sm text-white d-inline-block">查看信息</a>
                                        </div>
                                    </div>
                                </li>
                                <li class="dropdown-divider"></li>
                                <li class="user-list"><a class="px-3 py-2" href="#"><i class="ti-user"></i> 个人信息</a>
                                </li>
                                <li class="dropdown-divider"></li>
                                <li class="user-list"><a class="px-3 py-2" href="#"><i class="fa fa-power-off"></i> 退出登录</a>
                                </li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li class="sidebar-item">
                        <a class="sidebar-link has-arrow waves-effect waves-dark"
                           href="javascript:void(0)">
                            <i class="mdi mdi-lock"></i>
                            <span class="hide-menu">内部管理</span>
                        </a>
                        <ul class="collapse first-level">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">部门</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">员工</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">角色</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">资源</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">权限</span>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link has-arrow waves-effect waves-dark"
                           href="javascript:void(0)">
                            <i class="mdi mdi-account-multiple"></i>
                            <span class="hide-menu">用户管理</span>
                        </a>
                        <ul class="collapse first-level">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">驾校</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">教练</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">学员</span>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link has-arrow waves-effect waves-dark"
                           href="javascript:void(0)">
                            <i class="mdi mdi-book-open-variant"></i>
                            <span class="hide-menu">考试管理</span>
                        </a>
                        <ul class="collapse first-level">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">考场</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">科目1科目4</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">科目2科目3</span>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="sidebar-item selected">
                        <a class="sidebar-link has-arrow waves-effect waves-dark active"
                           href="javascript:void(0)">
                            <i class="mdi mdi-note-multiple"></i>
                            <span class="hide-menu">数据管理</span>
                        </a>
                        <ul class="collapse first-level in">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">省份</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">城市</span>
                                </a>
                            </li>
                            <li class="sidebar-item active">
                                <a href="#" class="sidebar-link active">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">数据字典类型</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">数据字典条目</span>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link has-arrow waves-effect waves-dark"
                           href="javascript:void(0)">
                            <i class="mdi mdi-coin"></i>
                            <span class="hide-menu">交易管理</span>
                        </a>
                        <ul class="collapse first-level">
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">提现</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="#" class="sidebar-link">
                                    <i class="mdi mdi-border-outside"></i>
                                    <span class="hide-menu">订单</span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
        <!-- Bottom points-->
        <div class="sidebar-footer">
            <!-- item-->
            <a href="" class="link" data-toggle="tooltip" title="设置"><i class="ti-settings"></i></a>
            <!-- item-->
            <a href="" class="link" data-toggle="tooltip" title="消息"><i class="mdi mdi-gmail"></i></a>
            <!-- item-->
            <a href="" class="link" data-toggle="tooltip" title="退出"><i class="mdi mdi-power"></i></a>
        </div>
        <!-- End Bottom points-->
    </aside>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-12">
                <h3 class="text-themecolor">数据字典类型</h3>
            </div>
        </div>

        <!-- ============================================================== -->
        <!-- End Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <div class="row">
                <div class="col-md-4 col-12 form-group">
                    <input type="text" class="form-control" placeholder="名称、值、简介">
                </div>
                <div class="col-md-4 col-6 form-group">
                    <button class="btn btn-secondary waves-effect waves-light mr-1">
                        <span class="btn-label"><i class="fas fa-redo"></i></span> 重置
                    </button>
                    <button class="btn btn-info waves-effect waves-light mr-1">
                        <span class="btn-label"><i class="fas fa-search"></i></span> 搜索
                    </button>
                </div>
                <div class="col-md-4 col-6 form-group d-flex justify-content-end">
                    <button id="btn-add"
                            class="btn btn-info waves-effect waves-light mr-2">
                        <span class="btn-label"><i class="fas fa-plus"></i></span> 添加
                    </button>
                    <button id="btn-batch-remove"
                            class="btn btn-danger waves-effect waves-light disabled">
                        <span class="btn-label"><i class="fas fa-trash"></i></span> 批量删除
                    </button>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="table-responsive">
                            <table class="table">
                                <thead class="bg-inverse text-white">
                                <tr>
                                    <th>
                                        <input type="checkbox" id="cb_th" class="material-inputs filled-in chk-col-light-blue">
                                        <label for="cb_th"></label>
                                    </th>
                                    <th>名称</th>
                                    <th>值</th>
                                    <th>简介</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list data as item>
                                    <tr>
                                    <td>
                                        <input type="checkbox" id="cb_0" class="material-inputs filled-in chk-col-light-blue">
                                        <label for="cb_0"></label>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.value}</td>
                                    <td>${item.intro}</td>
                                    <td>
                                        <div class="btn-group">
                                            <button class="btn btn-info btn-edit"><i class="ti-pencil-alt"></i></button>
                                            <button class="btn btn-danger btn-remove"><i class="ti-trash"></i></button>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-12 form-group">
                    <label>
                        显示
                        <select class="form-control form-control-sm">
                            <option value="10">10</option>
                            <option value="25">25</option>
                            <option value="50">50</option>
                            <option value="100">100</option>
                        </select>
                        条
                    </label>
                    ，共5页，共100条
                </div>
                <div class="col-md-6 col-12 d-flex justify-content-end">
                    <ul class="pagination">
                        <li class="page-item disabled">
                            <a class="page-link" href="javascript:void(0)" tabindex="-1">上一页</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="javascript:void(0)">1</a></li>
                        <li class="page-item active">
                            <a class="page-link" href="javascript:void(0)">2</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="javascript:void(0)">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="javascript:void(0)">下一页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->
</div>
<!-- ============================================================== -->
<!-- End Wrapper -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- customizer Panel -->
<!-- ============================================================== -->
<aside class="customizer">
    <a href="javascript:void(0)" class="service-panel-toggle"><i class="fa fa-spin fa-cog"></i></a>
    <div class="fade show" id="pills-home">
        <div class="p-3 border-bottom">
            <!-- Sidebar -->
            <h5 class="font-medium mb-2 mt-2">布局设置</h5>
            <div class="checkbox checkbox-info mt-3">
                <input type="checkbox" name="theme-view" class="material-inputs" id="theme-view">
                <label for="theme-view"> <span>深夜模式</span> </label>
            </div>
            <div class="checkbox checkbox-info mt-2">
                <input type="checkbox" class="sidebartoggler material-inputs" name="collapssidebar" id="collapssidebar">
                <label for="collapssidebar"> <span>收起侧栏</span> </label>
            </div>
            <div class="checkbox checkbox-info mt-2">
                <input type="checkbox" name="sidebar-position" class="material-inputs" id="sidebar-position">
                <label for="sidebar-position"> <span>固定侧栏</span> </label>
            </div>
            <div class="checkbox checkbox-info mt-2">
                <input type="checkbox" name="header-position" class="material-inputs" id="header-position">
                <label for="header-position"> <span>固定头部</span> </label>
            </div>
            <div class="checkbox checkbox-info mt-2">
                <input type="checkbox" name="boxed-layout" class="material-inputs" id="boxed-layout">
                <label for="boxed-layout"> <span>盒子布局</span> </label>
            </div>
        </div>
        <div class="p-3 border-bottom">
            <!-- Logo BG -->
            <h5 class="font-medium mb-2 mt-2">Logo背景</h5>
            <ul class="theme-color m-0 p-0">
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin1"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin2"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin3"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin4"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin5"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-logobg="skin6"></a></li>
            </ul>
            <!-- Logo BG -->
        </div>
        <div class="p-3 border-bottom">
            <!-- Navbar BG -->
            <h5 class="font-medium mb-2 mt-2">导航栏背景</h5>
            <ul class="theme-color m-0 p-0">
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin1"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin2"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin3"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin4"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin5"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-navbarbg="skin6"></a></li>
            </ul>
            <!-- Navbar BG -->
        </div>
        <div class="p-3 border-bottom">
            <!-- Logo BG -->
            <h5 class="font-medium mb-2 mt-2">侧栏背景</h5>
            <ul class="theme-color m-0 p-0">
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin1"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin2"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin3"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin4"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin5"></a></li>
                <li class="theme-item list-inline-item mr-1"><a href="javascript:void(0)"
                                                                class="theme-link rounded-circle d-block"
                                                                data-sidebarbg="skin6"></a></li>
            </ul>
            <!-- Logo BG -->
        </div>
    </div>
</aside>

<div id="modal-form"
     class="modal fade" style="display: none;">
    <form class="pl-3 pr-3 needs-validation" action="#" novalidate>
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="modal-form-label">添加</h4>
                    <button class="close" data-dismiss="modal">×</button>
                </div>
                    <div class="modal-body">
                        <div class="form-group row">
                            <label for="name"
                                   class="col-3 col-form-label">名称</label>
                            <div class="col-9">
                                <input class="form-control" maxlength="15" placeholder="名称"
                                       name="name" id="name" required>
                                <div class="invalid-feedback">
                                    请输入名称
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="value"
                                   class="col-3 col-form-label">值</label>
                            <div class="col-9">
                                <input class="form-control" maxlength="15" placeholder="值"
                                       name="value" id="value" required>
                                <div class="invalid-feedback">
                                    请输入值
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="intro"
                                   class="col-3 col-form-label">简介简介</label>
                            <div class="col-9">
                                <textarea class="form-control" maxlength="100" rows="3"
                                          id="intro" name="intro" placeholder="请输入简介"></textarea>
                                <div class="invalid-feedback">
                                    请输入简介
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-light" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-info">保存</button>
                    </div>
            </div>
        </div>
    </form>
</div>

<!-- ============================================================== -->
<!-- All Jquery -->
<!-- ============================================================== -->
<script src="${ctx}/assets/libs/jquery/jquery.min.js"></script>
<!-- Bootstrap tether Core JavaScript -->
<script src="${ctx}/assets/libs/popper/popper.min.js"></script>
<script src="${ctx}/assets/libs/bootstrap/bootstrap.min.js"></script>
<!-- apps -->
<script src="${ctx}/assets/js/origin/app.js"></script>
<script src="${ctx}/assets/js/origin/app.init.js"></script>
<script src="${ctx}/assets/js/origin/app-style-switcher.js"></script>
<!-- Alert -->
<script src="${ctx}/assets/libs/sweetalert2/sweetalert2.all.min.js"></script>
<!-- slimscrollbar scrollbar JavaScript -->
<script src="${ctx}/assets/libs/perfect-scrollbar/perfect-scrollbar.jquery.min.js"></script>
<!-- Wave Effects -->
<script src="${ctx}/assets/js/origin/waves.js"></script>
<!-- Menu sidebar -->
<script src="${ctx}/assets/js/origin/sidebarmenu.js"></script>
<!-- Custom JavaScript -->
<script src="${ctx}/assets/js/origin/custom.js"></script>
<script src="${ctx}/assets/js/main.js"></script>
<script>
    $(() => {
        // 变量、常量
        const $modal = $('#modal-form')
        const $modalForm = $modal.find('form')
        const $modalFormLabel = $modalForm.find('#modal-form-label')
        const $batchDelete = $('#btn-batch-remove')
        const $thCb = $('.table thead th input[type=checkbox]')
        const $trCbs = $('.table tbody tr input[type=checkbox]')

        // 点击添加
        $('#btn-add').click(() => {
            $modalFormLabel.text('添加')
            $modal.modal()
        })

        // 点击编辑
        $('.btn-edit').click(() => {
            $modalFormLabel.text('编辑')
            $modal.modal()
        })

        // 点击批量删除
        $batchDelete.click(() => {
            $.confirmRequest('你将删除XXX条数据！', '正在拼命删除中...', (success, error) => {
                setTimeout(() => {
                    (Math.random() > 0.5) ? success('删除成功！') : error('删除失败！')
                }, 2000)
            })
        })

        // 点击删除
        $('.btn-remove').click(() => {
            $.confirmRequest('你将删除XXX！', '正在拼命删除中...', (success, error) => {
                setTimeout(() => {
                    (Math.random() > 0.5) ? success('删除成功！') : error('删除失败！')
                }, 2000)
            })
        })

        // checkbox处理
        $thCb.change(function () {
            $trCbs.prop('checked', $(this).is(":checked"))
            $trCbs.change()
        })
        $trCbs.change(function () {
            const $this = $(this)
            const $parent = $this.parents('tr')
            if ($this.is(":checked")) {
                $parent.addClass('active')
            } else {
                $parent.removeClass('active')
            }
            const len = $trCbs.filter(':checked').length
            if (len > 0) {
                $batchDelete.removeClass('disabled')
                if (len === $trCbs.length) {
                    $thCb.prop("checked", true)
                }
            } else {
                $batchDelete.addClass('disabled')
                $thCb.prop("checked", false)
            }
        })
    })
</script>
</body>

</html>