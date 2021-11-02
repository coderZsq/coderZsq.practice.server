package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Enable 模块驱动示例
 */
@EnableHelloWorld
public class EnableModuleDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(EnableModuleDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        String helloWorld = context.getBean("helloWorld", String.class);

        // Annotation -> AnnotationAttributes(Map)

        System.out.println(helloWorld);

        // 关闭 Spring 应用上下文
        context.close();
    }
}
