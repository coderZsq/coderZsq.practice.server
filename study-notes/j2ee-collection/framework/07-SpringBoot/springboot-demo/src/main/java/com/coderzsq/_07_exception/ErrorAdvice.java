package com.coderzsq._07_exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

// @ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public String handlerException(Exception ex, HandlerMethod hm) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }
}
