package org.geekbang.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

public interface ExceptionIntercepter {

    /**
     * @param proxy
     * @param method
     * @param args
     * @param throwable 异常信息
     */
    void intercept(Object proxy, Method method, Object[] args, Throwable throwable);
}
