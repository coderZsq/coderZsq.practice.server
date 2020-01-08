//公共组件
//数组传值时，不添加[]
$.ajaxSettings.traditional = true;
$.support.cors = true;

$(function () {
    //统一全局配置
    $.messager.model = {
        ok: {text: "确定"},
        cancel: {text: "取消"}
    };
})
//==========================================
// var baseUrl="http://yapi.demo.qunar.com/mock/66785"
var baseUrl="http://127.0.0.1:8080"
//删除
$(function () {
    $(".btn_delete").click(function () {
        var url = baseUrl+$(this).data("url");
        $.messager.confirm("温馨提示", "你确定要删除此数据吗?", function () {
            //删除
            $.get(url, function (data) {
                if (data.success) {
                    //删除成功
                    $.messager.confirm("温馨提示", "删除成功", function () {
                        window.location.reload();
                    })
                } else {
                    //删除失败
                    $.messager.popup(data.msg);
                }
            });
        });
    })
})

//跳转
$(function () {
    //编辑跳转
    $(function () {
        $(".btn_redirect").click(function () {
            var url = $(this).data("url");
            window.location.href = url;
        })
    })
})

//给字符串添加格式化的方法
String.prototype.format = function() {
    if(arguments.length == 0) return this;
    var obj = arguments[0];
    var s = this;
    for(var key in obj) {
        if(typeof(obj[key]) == "object"){
            if(obj[key] &&  obj[key]["name"]){
                s = s.replace(new RegExp("\\{\\{" + key + "\\}\\}", "g"), obj[key]["name"]);
            }else{
                s = s.replace(new RegExp("\\{\\{" + key + "\\}\\}", "g"), "");
            }
        }else{
            s = s.replace(new RegExp("\\{\\{" + key + "\\}\\}", "g"), obj[key]);
        }

    }
    return s;
}

//回显数据的方法
function render(select, data){
    for(var i=0;i<data.length;i++){
        $(select).append(template.format(data[i]));
    }
}

//删除数据
function deleteData(node){
    var url = baseUrl+$(node).data("url");
    $.messager.confirm("温馨提示", "你确定要删除此数据吗?", function () {
        //删除
        $.ajax({
            url:url,
            method:"get",
            xhrFields: { withCredentials: true },
            success:function (data) {
                if (data.success) {
                    //删除成功
                    $.messager.confirm("温馨提示", "删除成功", function () {
                        window.location.reload();
                    })
                } else {
                    //删除失败
                    $.messager.popup(data.msg);
                }
            }
        })
    });
}
//提交表单数据
$(function () {
    $(".submitBtn").click(function () {
        $.ajax({
            url:baseUrl+$("#editForm").attr("action"),
            method:"post",
            data:$("#editForm").serialize(),
            xhrFields: { withCredentials: true },
            success:function (data) {
                if(data.success){
                    window.location.reload();
                }else{
                    $.messager.alert("温馨提示", data.msg)
                }
            }
        })
    })
})

//获取地址栏参数
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null) return unescape(r[2]);
    return null;
}
