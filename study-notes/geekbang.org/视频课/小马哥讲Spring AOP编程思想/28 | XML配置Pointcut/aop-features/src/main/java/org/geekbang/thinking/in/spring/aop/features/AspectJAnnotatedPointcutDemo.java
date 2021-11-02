package org.geekbang.thinking.in.spring.aop.features;

import org.geekbang.thinking.in.spring.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut 示例
 */
@Configuration // Configuration class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AspectJAnnotatedPointcutDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotatedPointcutDemo.class, AspectConfiguration.class);
        context.refresh();

        AspectJAnnotatedPointcutDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotatedPointcutDemo.class);

        aspectJAnnotationDemo.execute();

        context.close();
    }

    public void execute() {
        System.out.println("execute()...");
    }
}
