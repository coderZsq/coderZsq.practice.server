package org.geekbang.thinking.in.spring.aop.overview;

/**
 * 静态代理示例
 */
public class StaticProxyDemo {

    public static void main(String[] args) {
        EchoService echoService = new ProxyEchoService(new DefaultEchoService());
        echoService.echo("Hello,World");
    }
}
