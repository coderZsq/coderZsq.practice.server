package cn.wolfcode.wolf2w.exception;

/**
 * 自定义逻辑异常处理
 * 用于给用户提示
 */
public class LogicException extends RuntimeException {
    public LogicException(String message) {
        super(message);
    }
}
