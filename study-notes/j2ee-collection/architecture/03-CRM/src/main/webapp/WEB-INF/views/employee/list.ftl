<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
<#include "../common/link.ftl"/>
    <script>
        $(function () {
            jQuery.ajaxSettings.traditional = true;
            $("#deleteAll").change(function () {
                $('.selectOne').prop('checked',this.checked);
            });

            $(".btn_batchDelete").click(function () {
                $.messager.model = {
                    ok:{text:"确定"},
                    cancel:{text:"取消"}
                };

                if ($('.selectOne:checked').size() == 0) {
                    $.messager.alert("温馨提示","请选择要删除的员工");
                    return;
                }

                $.messager.confirm("温馨提示","你确定要删除吗?",function () {
                    var ids = $.map($('.selectOne:checked'),function (item) {
                        return $(item).data("id");
                    });
                    $.get("/employee/batchDelete",{ ids : ids } ,function (data) {
                        handleResult(data)
                    })
                })
            });

            $(".btn_import").click(function () {
                $("#editModal").modal("show");
            });

            $(".btn_save").click(function () {
               $("#importForm").ajaxSubmit(function (data) {
                   handleResult(data);
               })
            })
        })
        
    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<#include "../common/navbar.ftl"/>
    <!--菜单回显-->
<#assign currentMenu="employee"/>
<#include "../common/menu.ftl"/>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!''}" placeholder="请输入姓名/邮箱">
                        </div>
                        <div class="form-group">
                            <label for="dept"> 部门:</label>
                            <select class="form-control" id="dept" name="deptId">
                                <option value="-1">全部</option>
                                <#list depts as d>
                                    <option value="${d.id}">${d.name}</option>
                                </#list>
                            </select>
                            <script>
                                $("#dept option[value='${(qo.deptId)!}']").prop("selected", true);
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                        <a href="/employee/input.do" class="btn btn-success btn_redirect" >
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                        <a href="javascript:;" class="btn btn-danger btn_batchDelete" data-url="/employee/batchDelete">
                            <span class="glyphicon glyphicon-trash"></span> 批量删除
                        </a>
                        <a href="/employee/exportXls" target="_blank" class="btn btn-warning">
                            <span class="glyphicon glyphicon-export"></span> 导出
                        </a>
                        <a href="javascript:;" class="btn btn-warning btn_import">
                            <span class="glyphicon glyphicon-import"></span> 导入
                        </a>
                    </form>
                </div>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th><input type="checkbox" id="deleteAll"></th>
                            <th>编号</th>
                            <th>名称</th>
                            <th>email</th>
                            <th>年龄</th>
                            <th>部门</th>
                            <th>操作</th>
                        </tr>
                    <#--list,要遍历的集合 as 变量-->
                    <#list pageInfo.list as entity>
                        <tr>
                            <td><input type="checkbox" class="selectOne" data-id="${entity.id}"></td>
                            <#--从0开始,需要加1-->
                            <td>${entity_index + 1}</td>
                            <td>${entity.name}</td>
                            <td>${entity.email}</td>
                            <td>${entity.age}</td>
                            <td>${(entity.dept.name)!}</td>
                            <td>
                                <a href="/employee/input.do?id=${entity.id}" class="btn btn-info btn-xs btn-input" >
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                                <a href="javascript:;" data-url="/employee/delete.do?id=${entity.id}"
                                   class="btn btn-danger btn-xs btn-delete">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                    </#list>
                    </table>
                    <!--分页-->
                <#include "../common/page.ftl"/>
                </div>
            </div>

        </section>
    </div>
    <#include "../common/footer.ftl"/>


    <#--导入模态框-->
    <div class="modal fade" id="editModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">员工导入</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" action="/employee/importXls" method="post" id="importForm" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <input type="file" name="file" accept="application/vnd.ms-excel"><br/>
                                <a href="/template/employee_import.xls" class="btn btn-success">
                                    <span class="glyphicon glyphicon-download-alt"></span> 下载模板
                                </a>
                            </div>

                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn_save">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

