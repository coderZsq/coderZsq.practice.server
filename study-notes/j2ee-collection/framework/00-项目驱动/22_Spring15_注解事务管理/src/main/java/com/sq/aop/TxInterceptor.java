package com.sq.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TxInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 开启事务
        Object result = null;
        try {
            result = invocation.proceed();
            // 提交事务
        } catch (Exception e) {
            // 回滚事务
        }
        return result;
    }
}
