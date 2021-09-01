package org.geekbang.thinking.in.spring.aop.features;

import org.aspectj.lang.annotation.Aspect;
import org.geekbang.thinking.in.spring.aop.overview.EchoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Aspect        // 声明为 Aspect 切面
@Configuration // Configuration class
public class ProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

        System.out.println(echoService.echo("Hello,World"));

        context.close();
    }
}
