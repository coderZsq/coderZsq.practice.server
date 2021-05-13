package cn.wolfcode.netty.im.webserver.exception;

/**
 * @author Leon
 * @date 2021/5/5
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
