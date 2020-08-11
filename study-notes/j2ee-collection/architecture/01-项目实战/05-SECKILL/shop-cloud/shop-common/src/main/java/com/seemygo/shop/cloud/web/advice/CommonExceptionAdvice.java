package com.seemygo.shop.cloud.web.advice;

import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.resp.CodeMsg;
import com.seemygo.shop.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result<?> serverExceptionHandler(Exception ex) {
        log.error("[server exception]", ex);
        return Result.defaultError();
    }

    @ExceptionHandler(BindException.class)
    public Result<?> serverExceptionHandler(BindException ex) {
        List<ObjectError> allErrors = ex.getAllErrors();
        ObjectError error = allErrors.get(0);
        String message = error.getDefaultMessage();
        // 1个请求 -》参数错误 -》用户名密码错误
        // 多个请求 -》参数错误 -》用户名密码错误、用户名格式不正确、用户名不能为空
        // 静态变量：共享资源：多线程访问：线程安全问题
        return Result.error(new CodeMsg(CodeMsg.PARAM_ERROR.getCode(), message));
    }

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException ex) {
        CodeMsg codeMsg = ex.getCodeMsg();
        log.error("[business exception] business handle failed, code msg is:{code:{}, msg:{}}", codeMsg.getCode(), codeMsg.getMsg());
        return Result.error(codeMsg);
    }
}
