package com.sq.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("1--------------------");
        // 调用目标对象的目标方法
        Object result = invocation.proceed();
        System.out.println("2--------------------");
        return result;
    }
}
