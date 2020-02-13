package com.coderZsq._21_upload;

// 业务逻辑异常
public class LogicException extends RuntimeException {

    /**
     *
     * @param message 异常信息
     * @param cause 异常的根本原因
     */
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(String message) {
        super(message);
    }
}
