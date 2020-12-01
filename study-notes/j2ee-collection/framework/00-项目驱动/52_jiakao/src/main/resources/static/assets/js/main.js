$(() => {
    // 表单验证
    const forms = document.getElementsByClassName('needs-validation')
    Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })

    // 鼠标悬停 - 下拉菜单
    const $dropdown = $('li.dropdown .dropdown-toggle')
    $('li.dropdown').mouseover(function () {
        $dropdown.dropdown('show')
    }).mouseout(function () {
        $dropdown.dropdown('hide')
    })
})

/* ============================================================== */
/* 自定义函数 */
/* ============================================================== */
/**
 * 判断一个对象是不是字符串类型
 * @param obj
 */
$.isString = function (obj) {
    return (typeof obj === 'string') && obj.constructor === String;
}

/**
 * 带确认的请求
 * @param confirm 确认对话框的内容
 * @param loading 加载对话框的内容
 * @param asyncOperation 需要执行的异步请求操作
 */
$.confirmRequest = function (confirm, loading, asyncOperation) {
    if ($.isString(confirm)) {
        confirm = {text: confirm}
    }

    Swal.fire({
        title: confirm.title || '你确定？',
        text: confirm.text,
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#DD6B55',
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        allowOutsideClick: false
    }).then((result) => {
        if (!result.value) return

        $.loadingRequest(loading, asyncOperation)
    });
}

/**
 * 带遮挡的请求
 * @param loading 加载对话框的内容
 * @param asyncOperation 需要执行的异步请求操作
 */
$.loadingRequest = function (loading, asyncOperation) {
    if ($.isString(loading)) {
        loading = {title: loading}
    }

    // 弹框
    Swal.fire({
        title: loading.title,
        allowOutsideClick: false
    })
    Swal.showLoading()

    // 发送异步请求
    asyncOperation && asyncOperation((success, fn) => {
        if ($.isString(success)) {
            success = {title: success}
        }
        Swal.fire({
            type: 'success',
            title: success.title,
            confirmButtonText: '好的',
            allowOutsideClick: false
        }).then(() => {
            setTimeout(() => {
                // Swal.close()
                console.log(fn)
                // fn && fn()
            }, 1500)
        })
    }, (error, fn) => {
        if ($.isString(error)) {
            error = {title: error}
        }
        Swal.fire({
            type: 'error',
            title: error.title,
            timer: 1500,
            confirmButtonText: '好的',
            allowOutsideClick: false
        })
    })
}