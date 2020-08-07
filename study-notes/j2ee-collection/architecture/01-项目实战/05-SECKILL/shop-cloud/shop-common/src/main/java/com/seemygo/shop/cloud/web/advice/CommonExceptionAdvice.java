package com.seemygo.shop.cloud.web.advice;

import com.seemygo.shop.cloud.exception.BusinessException;
import com.seemygo.shop.cloud.resp.CodeMsg;
import com.seemygo.shop.cloud.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public Result<?> serverExceptionHandler(Exception ex) {
        log.error("[server exception]", ex);
        return Result.defaultError();
    }

    @ExceptionHandler(BusinessException.class)
    public Result<?> businessExceptionHandler(BusinessException ex) {
        CodeMsg codeMsg = ex.getCodeMsg();
        log.error("[business exception] business handle failed, code msg is:{code:{}, msg:{}}", codeMsg.getCode(), codeMsg.getMsg());
        return Result.error(codeMsg);
    }
}
