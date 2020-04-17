<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <#include "../common/link.ftl">
    <script>
        $(function () {
            $(".btn_reload").click(function () {
                var url = $(this).data("url");
                $.messager.confirm("温馨提示","加载权限会耗时，确定要操作么?",function () {
                    $.get(url,function (data) {
                        if(data.success){
                            //加载成功
                            $.messager.confirm("温馨提示","加载成功", function () {
                                window.location.reload();
                            })
                        }
                    });
                })
            })
        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="permission">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box" >
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage">
                    <a href="javascript:;" data-url="/permission/reload.do" class="btn btn-success btn_reload" style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span>  重新加载
                    </a>
                </form>

                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <#list pageInfo.list as entity>
                       <tr>
                           <td>${entity_index+1}</td>
                           <td>${entity.name}</td>
                           <td>${entity.expression}</td>
                           <td>
                               <a href="javascript:;"data-url="/permission/delete.do?id=${entity.id}" class="btn btn-danger btn-xs btn-delete" >
                                   <span class="glyphicon glyphicon-trash"></span> 删除
                               </a>
                           </td>
                       </tr>
                    </#list>
                    </table>
                <#--分页-->
                <#include "../common/page.ftl"/>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
</body>
</html>
