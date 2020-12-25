package com.sq.jk.common.util;

import com.sq.jk.common.exception.CommonException;
import com.sq.jk.pojo.query.PageQuery;
import com.sq.jk.pojo.result.R;

public class Rs {
    public static final String K_COUNT = "count";

    public static R error(String msg) {
        return new R(false, msg);
    }

    public static R error(Throwable t) {
        if (t instanceof CommonException) {
            CommonException e = (CommonException) t;
            return new R(e.getCode(), e.getMessage());
        } else {
            return error(t.getMessage());
        }
    }

    public static R ok(PageQuery query) {
        R r = new R(query.getData());
        r.put(K_COUNT, query.getCount());
        return r;
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
}
