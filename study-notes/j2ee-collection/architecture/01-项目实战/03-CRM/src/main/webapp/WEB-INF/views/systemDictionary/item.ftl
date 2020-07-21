<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据字典管理</title>
    <#include "../common/link.ftl">
    <script type="text/javascript">
        //编辑
        $(function () {
            $(".inputBtn").click(function () {

                var pid = '${qo.parentId}';

                if(!pid || pid == -1){
                    $.messager.alert("温馨提示", "请先选中字典目录")
                    return ;
                }

                //修改模态框标题
                $(".modal-title").html("数据字典明细添加");
                //数据复原
                $("#editForm")[0].reset();

                //字典目录
                var parentName = $("#dicDirUl li.active").find("a").html();
                $("#editForm input[name='parentName']").val(parentName);

                //弹出模态框
                $("#editModal").modal("show");


                //数据字典回显数据
                var data = $(this).data("json");
                if(data){
                    $(".modal-title").html("数据字典明细编辑");
                    $("#editForm input[name='id']").val(data.id);
                    $("#editForm input[name='title']").val(data.title);
                    $("#editForm input[name='sequence']").val(data.sequence);
                }
            })
        })

        //提交编辑表单
        $(function () {
            $(".btn_submit").click(function () {
                $("#editForm").ajaxSubmit(function (data) {
                    if(data.success){
                        $.messager.confirm("温馨提示", "操作成功", function () {
                            window.location.reload();
                        })
                    }else{
                        $.messager.alert("温馨提示", data.msg)
                    }
                })
            })
        })
        //字典目录点击
        $(function () {
            $(".dicDir").click(function () {
                var pid = $(this).data("pid");
                $("#parentId").val(pid);
                //表单提交
                $("#searchForm").submit();
            })
        })
    </script>
</head>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="systemDictionaryItem">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>数据字典明细管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/systemDictionaryItem/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <input type="hidden" name="parentId" id="parentId" value="${(qo.parentId)!''}">
                    </form>

                    <br>
                    <div class="row">

                        <div class="  col-sm-3">
                            <div class="panel panel-info">
                                <div class="panel-heading"><strong>字典明细</strong></div>
                                <ul class="list-group" id="dicDirUl">
                                    <#list dics as d>
                                        <li class="list-group-item"><a class="dicDir" data-pid="${d.id}" href="javascript:;">${d.title}</a></li>
                                    </#list>
                                    <script>
                                        $(".dicDir[data-pid='${qo.parentId}']").closest("li").addClass("active");
                                        $(".dicDir[data-pid='${qo.parentId}']").css("color","white")
                                    </script>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-9">
                            <a href="JavaScript:;" class="btn btn-success inputBtn"><span class="glyphicon glyphicon-plus"></span> 添加明细</a>
                            <table class="table table-striped table-hover table-bordered">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>标题</th>
                                    <th>序号</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                               <#list pageInfo.list as entity>
                                   <tr>
                                       <td>${entity_index+1}</td>
                                       <td>${entity.title}</td>
                                       <td>${entity.sequence!''}</td>
                                       <td>
                                           <a class="btn btn-info btn-xs inputBtn" href="javascript:;" data-json='${entity.jsonString}'>
                                               <span class="glyphicon glyphicon-pencil"></span>编辑
                                           </a>
                                           <a href="javascript:;" class="btn btn-danger btn-xs btn-delete"
                                              data-url="/systemDictionaryItem/delete.do?id=${entity.id}">
                                               <span class="glyphicon glyphicon-trash"></span>删除
                                           </a>
                                       </td>
                                   </tr>
                               </#list>
                            </table>
                            <#--分页-->
                            <#include "../common/page.ftl"/>
                        </div>
                    </div>


                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>


<#--数据字典编辑模态框-->
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">数据字典编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/systemDictionaryItem/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${(qo.parentId)!''}" name="parentId">
                    <input type="hidden" value="" name="id">
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">字典目录：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="parentName" disabled>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">明细标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" value="" placeholder="请输入数据字典明细标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">明细序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="sequence" value="" placeholder="请输入数据字典明细编号">
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn_submit" >保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
