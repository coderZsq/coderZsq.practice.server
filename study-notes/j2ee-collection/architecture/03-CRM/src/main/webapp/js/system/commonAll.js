$(function () {
    $.messager.model = {
        ok:{ text: "确定"},
        cancel: { text: "取消"}
    };
    $(".btn-delete").click(function () {

        var url = $(this).data("url");
        $.messager.confirm("温馨提示", "你确定要删除吗?", function() {
            $.post(url,function (data) {
                handleResult(data);
            });
        });
    });
});
function handleResult(data) {
    if (data.success) {
        $.messager.alert("温馨提示", "执行成功,1s后跳转");
        setTimeout(function () {
            window.location.reload();
        },1000)
    } else {
        $.messager.alert("温馨提示", data.msg);
    }
}