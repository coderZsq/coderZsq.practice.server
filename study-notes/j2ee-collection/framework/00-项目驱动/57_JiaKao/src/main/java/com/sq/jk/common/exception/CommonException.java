package com.sq.jk.common.exception;

import com.sq.jk.pojo.result.CodeMsg;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException {
    private static final int CODE_DEFAULT = CodeMsg.BAD_REQUEST.getCode();
    private int code;

    public CommonException() {
        this(null);
    }

    public CommonException(String msg) {
        this(msg, null);
    }

    public CommonException(int code, String msg) {
        this(code, msg, null);
    }

    public CommonException(String msg, Throwable cause) {
        this(CODE_DEFAULT, msg, cause);
    }

    public CommonException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public CommonException(CodeMsg codeMsg, Throwable cause) {
        this(codeMsg.getCode(), codeMsg.getMsg(), cause);
    }

    public int getCode() {
        return code;
    }
}
