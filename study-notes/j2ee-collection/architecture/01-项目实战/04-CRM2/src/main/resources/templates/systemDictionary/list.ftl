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

                //弹出模态框
                $("#editModal").modal("show");
                //修改模态框标题
                $(".modal-title").html("数据字典添加");
                //数据复原
                $("#editForm")[0].reset();

                //数据字典回显数据
                var data = $(this).data("json");
                if(data){
                    $(".modal-title").html("数据字典编辑");
                    $("input[name='id']").val(data.id);
                    $("input[name='title']").val(data.title);
                    $("input[name='sn']").val(data.sn);
                    $("input[name='intro']").val(data.intro);
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
    </script>
</head>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="systemDictionary">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>数据字典管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/systemDictionary/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <a href="JavaScript:;" class="btn btn-success inputBtn"><span class="glyphicon glyphicon-plus"></span> 添加</a>
                    </form>

                    <table class="table table-striped table-hover" >
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>标题</th>
                            <th>编码</th>
                            <th>简介</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                       <#list pageInfo.list as entity>
                           <tr>
                               <td>${entity_index+1}</td>
                               <td>${entity.title}</td>
                               <td>${entity.sn}</td>
                               <td>${entity.intro!''}</td>
                               <td>
                                   <a class="btn btn-info btn-xs inputBtn" href="javascript:;" data-json='${entity.jsonString}'>
                                       <span class="glyphicon glyphicon-pencil"></span>编辑
                                   </a>
                                   <a href="/systemDictionaryItem/list.do?parentId=${entity.id}" class="btn btn-primary btn-xs">
                                       <span class="glyphicon glyphicon-plus"></span>  添加明细
                                   </a>
                               </td>
                           </tr>
                       </#list>
                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl">
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
                <form class="form-horizontal" action="/systemDictionary/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="" name="id">
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">数据字典标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" value="" placeholder="请输入数据字典标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">数据字典编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="sn" value="" placeholder="请输入数据字典编号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="col-sm-3 control-label">数据字典简介：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control"  name="intro" value="" placeholder="请输入数据字典简介">
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
