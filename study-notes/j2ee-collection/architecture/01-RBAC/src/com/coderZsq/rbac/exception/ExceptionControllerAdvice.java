package com.coderZsq.rbac.exception;

import com.coderZsq.rbac.utils.PageResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // AOP
@ResponseBody
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public PageResult handlerException(Exception ex) {
        String message = ex.getMessage();
        return PageResult.error(message);
    }
}
