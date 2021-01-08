package com.sq.jk.common.util;

import com.sq.jk.common.exception.CommonException;
import com.sq.jk.pojo.query.PageQuery;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.result.R;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Rs {
    public static final String K_COUNT = "count";

    public static R error(String msg) {
        return new R(false, msg);
    }

    public static R error() {
        return new R(false);
    }

    public static R error(Throwable t) {
        if (t instanceof CommonException) {
            CommonException ce = (CommonException) t;
            return new R(ce.getCode(), ce.getMessage());
        } else if (t instanceof BindException) {
            BindException be = (BindException) t;
            List<ObjectError> errors = be.getBindingResult().getAllErrors();
            // 函数式编程的方式: stream
            List<String> defaultMsgs = errors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            // List<String> defaultMsgs = new ArrayList<>(errors.size());
            // for (ObjectError error : errors) {
            //     defaultMsgs.add(error.getDefaultMessage());
            // }
            String msg = StringUtils.collectionToDelimitedString(defaultMsgs, ", ");
            return error(msg);
        } else { // 其他异常
            // return error(t.getMessage());
            return error();
        }
    }

    public static R ok(PageQuery query) {
        R r = new R(query.getData());
        r.put(K_COUNT, query.getCount());
        return r;
    }

    public static R ok(CodeMsg msg) {
        return new R(true, msg.getMsg());
    }

    public static R ok(String msg) {
        return new R(true, msg);
    }

    public static R ok(Object data) {
        return new R(data);
    }

    public static R ok() {
        return new R();
    }

    public static R raise(String msg) throws CommonException {
        throw new CommonException(msg);
    }

    public static R raise(CodeMsg codeMsg) throws CommonException {
        throw new CommonException(codeMsg);
    }
}
