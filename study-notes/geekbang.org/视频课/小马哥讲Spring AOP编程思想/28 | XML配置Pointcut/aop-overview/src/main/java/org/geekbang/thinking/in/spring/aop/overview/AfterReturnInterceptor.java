package org.geekbang.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

/**
 * (方法返回) 后置拦截器
 */
public interface AfterReturnInterceptor {

    /**
     * 后置执行
     * @param proxy
     * @param method
     * @param args
     * @param returnResult 执行方法返回结果
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
