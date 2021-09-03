package org.geekbang.thinking.in.spring.aop.features;

import org.aopalliance.aop.Advice;
import org.geekbang.thinking.in.spring.aop.features.interceptor.EchoServiceMethodInterceptor;
import org.geekbang.thinking.in.spring.aop.overview.DefaultEchoService;
import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyCreatorSupport;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link AdvisedSupportListener} 示例
 */
public class AdvisedSupportListenerDemo {
    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象 (被代理)
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTargetClass(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP 配置对象: " + advised + " 已激活");
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP 配置对象: " + advised + " 已变化");
            }
        });
        // 获取代理对象
        // 激活事件触发 createAopProxy() <- getProxy()
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        proxyFactory.addAdvice(new Advice() {
        });
    }
}
