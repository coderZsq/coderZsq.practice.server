<#assign ctx="${springMacroRequestContext.getContextPath()}">

<!DOCTYPE html>
<html dir="ltr" lang="zh">
<head>
    <#include "common/style.ftlh">
</head>
<body>
<#include "common/preloader.ftlh">
<!-- ============================================================== -->
<!-- Main wrapper - style you can find in pages.scss -->
<!-- ============================================================== -->
<div id="main-wrapper">
    <#include "common/topbar.ftlh">
    <#include "common/leftsidebar.ftlh">
    <!-- ============================================================== -->
    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="row page-titles">
            <div class="col-12">
                <h3 class="text-themecolor">数据字典条目</h3>
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
            <form id="search-form" action="${ctx}/dictItems/list">
                <input type="hidden" name="page" value="${query.page}">
                <input type="hidden" name="size" value="${query.size}">
                <div class="row">
                        <div class="col-md-4 col-12 form-group">
                            <input type="text"
                                   name="keyword"
                                   value="${query.keyword!}"
                                   class="form-control" placeholder="名称、值、简介">
                        </div>
                        <div class="col-md-4 col-6 form-group">
                            <button type="button" class="btn btn-secondary waves-effect waves-light mr-1">
                                <span class="btn-label"><i class="fas fa-redo"></i></span> 重置
                            </button>
                            <button type="submit"
                                    id="search-btn"
                                    class="btn btn-info waves-effect waves-light mr-1">
                                <span class="btn-label"><i class="fas fa-search"></i></span> 搜索
                            </button>
                        </div>
                    <div class="col-md-4 col-6 form-group d-flex justify-content-end">
                        <button type="button" id="btn-add"
                                class="btn btn-info waves-effect waves-light mr-2">
                            <span class="btn-label"><i class="fas fa-plus"></i></span> 添加
                        </button>
                        <button type="button" id="btn-batch-remove"
                                class="btn btn-danger waves-effect waves-light disabled">
                            <span class="btn-label"><i class="fas fa-trash"></i></span> 批量删除
                        </button>
                    </div>
                </div>
            </form>

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
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list query.data as item>
                                    <tr>
                                    <td>
                                        <input type="checkbox" id="cb_0" class="material-inputs filled-in chk-col-light-blue">
                                        <label for="cb_0"></label>
                                    </td>
                                    <td>${item.name}</td>
                                    <td>${item.value}</td>
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

            <#include "common/pagination.ftlh">
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
<#include "common/customizer.ftlh">

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

<#include "common/script.ftlh">
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

        // 搜索表单
        const $searchForm = $('#search-form')
        // 搜索表单 - size
        const $hiddenSize = $searchForm.find('[name=size]')
        // 搜索表单 - page
        const $hiddenPage = $searchForm.find('[name=page]')
        // 搜索表单 - 搜索按钮
        const $searchBtn = $searchForm.find('#search-btn')
        $searchBtn.click(() => {
            $hiddenPage.val(1)
            $searchForm.submit()
        })

        // 监听size改变
        const $selectSize = $('select[name=size]')
        $selectSize.change(() => {
            <#--window.location.href = `${ctx}/dictTypes/list?size=` + $size.val() + "&keyword=" + $('[name=keyword]').val()-->
            $hiddenSize.val($selectSize.val())
            $hiddenPage.val(1);
            $searchForm.submit()
        })
        $selectSize.val(${query.size})

        // 处理分页信息
        $('ul.pagination').bootstrapPaginator({
            bootstrapMajorVersion: 3,
            currentPage: ${query.page},
            totalPages: ${query.pages},
            onPageClicked: (e1, e2, type, page) => {
                // 如果是当前页, 直接返回
                if (page === ${query.page}) return
                $hiddenPage.val(page)
                $searchForm.submit()
            }
        })
    })
</script>
</body>

</html>