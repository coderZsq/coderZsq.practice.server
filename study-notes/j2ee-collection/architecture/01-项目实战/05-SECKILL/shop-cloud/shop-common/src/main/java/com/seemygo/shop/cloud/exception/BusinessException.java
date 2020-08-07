package com.seemygo.shop.cloud.exception;

import com.seemygo.shop.cloud.resp.CodeMsg;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private CodeMsg codeMsg;

    public BusinessException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }
}
