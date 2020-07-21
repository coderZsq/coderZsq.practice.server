<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>移交历史管理</title>
    <#include "../common/link.ftl">
    <script>
        //编辑
        $(function () {
            $(".btn_input_modal").click(function () {
                //弹出模态框
                $("#editModal").modal("show");
                //修改模态框标题
                $(".modal-title").html("移交历史添加");
                //数据复原
                $("#editForm")[0].reset();

                //移交历史回显数据
                var data = $(this).data("json");
                if(data){
                    $(".modal-title").html("移交历史编辑");
                    $("input[name='id']").val(data.id);
                    $("input[name='name']").val(data.name);
                    $("input[name='sn']").val(data.sn);
                }
            })


        })

        //批量删除
        $(function () {
            $(".btn_batchDelete").click(function () {
                //1:提示是否选择
                if ($(".cb:checked").size() <= 0) {
                    $.messager.alert("温馨提示","请选择要删除数据")
                    return;
                }
                var url = $(this).data("url");
                //2：提示是否确定要删除
                $.messager.confirm("温馨提示", "你确定要删除选中数据么?", function () {
                    //获取选中数据id
                    var ids = $.map($(".cb:checked"), function (item) {
                        return $(item).data("id");
                    })
                    //删除
                    $.post(url, {ids: ids}, function (data) {
                        if (data.success) {
                            //删除成功
                            $.messager.confirm("温馨提示", "删除成功", function () {
                                window.location.reload();
                            })
                        } else {
                            //删除失败
                            $.messager.alert("温馨提示", data.msg)
                        }
                    });
                })
            })
        })
        $(function () {
            //导入
            $(".btn_import").click(function () {
                $("#editModal").modal("show");
            })
            //模态框提交：导入
            $(".btn_save").click(function () {
                $("#editForm").submit();
            })
        })

    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="customerTransfer">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>移交历史管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customerTransfer/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" >
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!""}" placeholder="请输入姓名或电话">
                        </div>
                        <div class="form-group">
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>查询</button>
                    </form>

                </div>
                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>客户姓名</th>
                        <th>操作日期</th>
                        <th>操作人</th>
                        <th>旧营销人员</th>
                        <th>新营销人员</th>
                        <th>移交原因</th>
                    </tr>
                    </thead>
               <#list pageInfo.list as entity>
                   <tr>
                       <td>${entity_index+1}</td>
                       <td>${entity.customer.name}</td>
                       <td>${entity.operateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                       <td>${entity.operator.name}</td>
                       <td>${entity.oldSeller.name}</td>
                       <td>${entity.newSeller.name}</td>
                       <td>${entity.reason}</td>
                   </tr>
               </#list>
                </table>
            <#include "../common/page.ftl">
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
</body>
</html>
