package org.geekbang.thinking.in.spring.aop.overview;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理示例
 */
public class JdkDynamicProxyDemo {

    // class $Proxy0 extend java.lang.reflect.Proxy implements EchoService {
    //      $Proxy0(InvocationHandler handler) {
    //          super(handler);
    //      }
    // }

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // $Proxy0
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");

        // $Proxy1
        Object proxy2 = Proxy.newProxyInstance(classLoader,
                new Class[]{Comparable.class},
                (proxy1, method, args1) -> {
                    return null;
                });
        System.out.println(proxy2);
    }
}
