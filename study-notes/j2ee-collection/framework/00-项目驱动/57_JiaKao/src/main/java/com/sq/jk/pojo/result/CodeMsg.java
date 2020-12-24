package com.sq.jk.pojo.result;

public enum CodeMsg {
    BAD_REQUEST(400, "请求出错"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

    WRONG_PASSWORD(800200, "密码错误");

    private final int code;
    private final String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
