package com.coderZsq.rbac.web.controller;

import com.coderZsq.rbac.common.BizException;
import com.coderZsq.rbac.utils.PageResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionController {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public PageResult handlerBizException(BizException bizException){
        String msg = bizException.getMsg();
        return PageResult.error(msg);
    };

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public PageResult handlerException(Exception exception){
        String msg = exception.getMessage();
        return PageResult.error(msg);
    };
}
