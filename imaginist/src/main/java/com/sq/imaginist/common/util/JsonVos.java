package com.sq.imaginist.common.util;

import com.sq.imaginist.common.exception.CommonException;
import com.sq.imaginist.pojo.result.CodeMsg;
import com.sq.imaginist.pojo.vo.DataJsonVo;
import com.sq.imaginist.pojo.vo.JsonVo;
import com.sq.imaginist.pojo.vo.PageJsonVo;
import com.sq.imaginist.pojo.vo.PageVo;

public class JsonVos {
    public static JsonVo error(String msg) {
        return new JsonVo(false, msg);
    }

    public static JsonVo error(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo error(int code, String msg) {
        return new JsonVo(code, msg);
    }

    public static JsonVo error() {
        return new JsonVo(false);
    }

    public static JsonVo ok(CodeMsg msg) {
        return new JsonVo(msg);
    }

    public static JsonVo ok(String msg) {
        return new JsonVo(true, msg);
    }

    public static <T> DataJsonVo<T> ok(T data) {
        return new DataJsonVo<>(data);
    }

    public static <T> PageJsonVo<T> ok(PageVo<T> pageVo) {
        PageJsonVo<T> pageJsonVo = new PageJsonVo<>();
        pageJsonVo.setMsg(CodeMsg.OPERATE_OK.getMsg());
        pageJsonVo.setCount(pageVo.getCount());
        pageJsonVo.setData(pageVo.getData());
        return pageJsonVo;
    }

    public static JsonVo ok() {
        return new JsonVo();
    }

    public static <T> T raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static <T> T raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
