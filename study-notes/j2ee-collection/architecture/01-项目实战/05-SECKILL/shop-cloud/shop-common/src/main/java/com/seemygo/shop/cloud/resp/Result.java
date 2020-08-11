package com.seemygo.shop.cloud.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    public static final String DEFAULT_SUCCESS_CODE = "00000";
    /**
     * 1 3 4
     * 第一位：
     * A|B|C
     *  A: 用户操作异常
     *  B: 业务异常
     *  C: 服务端异常
     * 中间三位：
     * 001：会员服务
     * 002：商品服务
     * 003：秒杀服务
     * 后四位：
     * 0010001：会员服务参数错误
     */
    public static final String DEFAULT_ERROR_CODE = "B0000001";
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";
    public static final String DEFAULT_ERROR_MSG = "服务器繁忙，请稍后再试";

    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MSG, data);
    }

    public static Result<?> defaultError() {
        return new Result<>(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MSG, null);
    }

    public static Result<?> error(CodeMsg codeMsg) {
        return new Result<>(codeMsg.getCode(), codeMsg.getMsg(), null);
}

    public boolean hasError() {
        // this -> code == 200 -> false
        // this -> code == 50xxxx -> true
        return !DEFAULT_SUCCESS_CODE.equals(this.getCode());
    }
}
