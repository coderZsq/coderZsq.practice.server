package com.sq.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 会拦截每一个bean的生命周期
 */
public class LogProcessor2 implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object target, String beanName) throws BeansException {
        // if (beanName.equals("person")) return target;
        if (!beanName.endsWith("Service")) return target;

        Enhancer enhancer = new Enhancer();
        // 可以省略
        // enhancer.setClassLoader(getClass().getClassLoader());
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new LogMethodInterceptor(target));
        return enhancer.create(); // 附加代码
    }

    private static class LogMethodInterceptor implements MethodInterceptor {
        private final Object target;

        public LogMethodInterceptor(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object proxy, Method method,
                                Object[] args, MethodProxy methodProxy)
                throws Throwable {
            String name = method.getName();
            // method.getReturnType();
            // method.getParameterTypes();

            boolean login = name.equals("login");

            if (login) {
                System.out.println("1-------------------");
            }
            Object result = method.invoke(target, args);
            if (login) {
                System.out.println("2-------------------");
            }
            return result;
        }
    }
}
