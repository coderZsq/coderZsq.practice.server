package com.coderZsq.rbac.common;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class BizException extends RuntimeException {
    private int code;
    private String msg;
    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
