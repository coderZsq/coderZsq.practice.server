package com.sq.jk.pojo.result;

import com.sq.jk.common.enhance.Jsonable;

import java.util.HashMap;

/**
 * 返回给用户的JSON对象
 */
public class R extends HashMap<String, Object> implements Jsonable {
    private static final String K_CODE = "code";
    private static final String K_MSG = "msg";
    private static final String K_DATA = "data";
    private static final int CODE_SUCCESS = 0;
    private static final int CODE_ERROR_DEFAULT = CodeMsg.BAD_REQUEST.getCode();

    public R setSuccess(boolean success) {
        return success ? setCode(CODE_SUCCESS) : setCode(CODE_ERROR_DEFAULT);
    }

    public R setCode(int code) {
        put(K_CODE, code);
        return this;
    }

    public R setMsg(String msg) {
        put(K_MSG, msg);
        return this;
    }

    public R setData(Object data) {
        put(K_DATA, data);
        return this;
    }
}
