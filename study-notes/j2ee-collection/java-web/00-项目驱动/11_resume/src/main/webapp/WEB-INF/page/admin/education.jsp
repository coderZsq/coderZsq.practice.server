<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>小码哥简历管理-教育信息</title>
    <%@ include file="common/head.jsp" %>
</head>

<body class="theme-blue">

<%@ include file="common/middle.jsp" %>

<section class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="card">
                    <div class="header">
                        <h2>教育信息</h2>
                    </div>
                    <div class="body table-responsive">
                        <div class="menus">
                            <div class="buttons">
                                <button type="button" class="btn bg-blue waves-effect btn-sm"
                                        onclick="add()">
                                    <i class="material-icons">add</i>
                                    <span>添加</span>
                                </button>
                                <button type="button"
                                        class="btn bg-pink waves-effect btn-sm removeAll disabled"
                                        disabled
                                        onclick="removeAll()">
                                    <i class="material-icons">delete</i>
                                    <span>删除选中</span>
                                </button>
                            </div>
                        </div>

                        <table class="table table-bordered table-hover table-striped">
                            <thead>
                            <tr>
                                <th>
                                    <div class="switch">
                                        <label><input type="checkbox"><span
                                                class="lever switch-col-blue"></span></label>
                                    </div>
                                </th>
                                <th>名称</th>
                                <th>开始</th>
                                <th>结束</th>
                                <th>类型</th>
                                <th>简介</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <form id="remove-form" action="${ctx}/education/remove" method="post">
                                <c:forEach items="${educations}" var="education">
                                    <tr>
                                        <td>
                                            <div class="switch">
                                                <label><input type="checkbox" name="id" value="${education.id}"><span
                                                        class="lever switch-col-blue"></span></label>
                                            </div>
                                        </td>
                                        <td>${education.name}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${education.beginDay}"/></td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${education.endDay}"/></td>
                                        <td>${education.typeString}</td>
                                        <td>${education.intro}</td>
                                        <td>
                                            <button type="button" class="btn bg-blue waves-effect btn-xs"
                                                    onclick="edit(${education.JSON})">
                                                <i class="material-icons">edit</i>
                                                <span>编辑</span>
                                            </button>
                                            <button type="button" class="btn bg-pink waves-effect btn-xs"
                                                    onclick="remove('${education.id}', '${education.name}')">
                                                <i class="material-icons">delete</i>
                                                <span>删除</span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </form>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="common/foot.jsp"%>

<script>
    $('.menu .list .education').addClass('active')
    addValidatorRules('.form-validation')

    const $addFormBox = $('#add-form-box')
    const $addForm = $addFormBox.find('form')

    function add() {
        $addFormBox.modal()
        // 重置表单的内容
        $addForm[0].reset()

        // 原生的form对象有一个reset方法
        // document.getElement... document.query... 获取的是原生的DOM对象
        // $()获取的是jQuery包装过的对象, 并不是原生的DOM对象
    }

    // 函数 - 方法
    function edit(education) {
        add()

        // 填充表单信息
        for (const k in education) {
            $addForm.find('[name=' + k + ']').val(education[k])
        }
        // $addForm.find('[name=name]').val(education.name)
        // $addForm.find('[name=beginDay]').val(education.beginDay)
        // $addForm.find('[name=endDay]').val(education.endDay)
        // $addForm.find('[name=type]').val(education.type)
        // $addForm.find('[name=intro]').val(education.intro)
        // $addForm.find('[name=id]').val(education.id)
    }

    function remove(id, name) {
        swal({
            title: "你确定？",
            text: '你确定要删除【' + name + '】？',
            icon: 'warning',
            dangerMode: true,
            buttons: {
                cancel: '取消',
                confirm: '确定'
            }
        }).then(willDelete => {
            if (!willDelete) return
            // 点击了【确定】
            // window.location.href
            location.href = '${ctx}/education/remove?id=' + id

            // swal({
            //     title: '删除成功',
            //     text: '【' + name + '】已经被删除！',
            //     icon: 'success',
            //     timer: 1500,
            //     buttons: false
            // })
        })
    }

    function removeAll() {
        swal({
            title: "你确定？",
            text: "你确定要删除所有选中的记录？",
            icon: "warning",
            dangerMode: true,
            buttons: {
                cancel: "取消",
                confirm: "确定"
            }
        }).then(willDelete => {
            if (!willDelete) return

            // 拿到表单, 发送请求
            $('#remove-form').submit()
        })
    }

    const $set = $(".table tbody tr input[type=checkbox]")
    const $removeAll = $('.table-responsive .removeAll')
    $('.table thead th input[type=checkbox]').change(function () {
        let checked = $(this).is(":checked")
        if (checked) {
            $set.each(function () {
                $(this).prop("checked", true)
                $(this).parents('tr').addClass("active")
            })
            $removeAll.removeClass('disabled')
            $removeAll.prop('disabled', false)
        } else {
            $set.each(function () {
                $(this).prop("checked", false)
                $(this).parents('tr').removeClass("active")
            })
            $removeAll.addClass('disabled')
            $removeAll.prop('disabled', true)
        }
    })

    $set.change(function () {
        $(this).parents('tr').toggleClass("active")
        if ($('.table tbody tr input[type=checkbox]:checked').length > 0) {
            $removeAll.removeClass('disabled')
            $removeAll.prop('disabled', false)
        } else {
            $removeAll.addClass('disabled')
            $removeAll.prop('disabled', true)
        }
    })
</script>
</body>

</html>
