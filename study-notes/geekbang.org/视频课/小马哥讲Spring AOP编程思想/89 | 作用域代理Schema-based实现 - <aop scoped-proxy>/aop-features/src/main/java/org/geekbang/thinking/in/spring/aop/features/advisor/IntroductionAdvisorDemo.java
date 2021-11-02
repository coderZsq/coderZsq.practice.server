package org.geekbang.thinking.in.spring.aop.features.advisor;

import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * {@link IntroductionAdvisor} 示例
 */
public class IntroductionAdvisorDemo extends Object implements EchoService, Comparable<IntroductionAdvisorDemo> {

    public static void main(String[] args) {
        // EchoService 和 Comparable
        IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();
        // 使用该构造器会使得 IntroductionInfo 失效
        // target -> EchoService 和 Comparable
        ProxyFactory proxyFactory = new ProxyFactory(target);
        // ProxyFactory proxyFactory = new ProxyFactory();
        // proxyFactory.setTarget(target);
        // 添加 IntroductionAdvisor
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("BeforeAdvice : " + method);
            }
        }, new IntroductionInfo() {
            @Override
            public Class<?>[] getInterfaces() {
                return new Class[]{EchoService.class, Map.class};
            }
        }));

        Object proxy = proxyFactory.getProxy();

        EchoService echoService = (EchoService) proxy;

        echoService.echo("Hello, World");

        Map map = (Map) proxy;

        map.put("1", "A");

        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);
    }

    @Override
    public int compareTo(IntroductionAdvisorDemo o) {
        return 0;
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return "IntroductionAdvisorDemo : " + message;
    }
}
