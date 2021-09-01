package org.geekbang.thinking.in.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * {@link LiveBeansView} 示例
 */
public class LiveBeanViewDemo {

    public static void main(String[] args) throws IOException {

        // 添加 LiveBeansView 的 ObjectName 的 domain
        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME, "org.geekbang.thinking.in.spring");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class
        context.register(LiveBeanViewDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println("按任意键继续...");
        System.in.read();

        // 关闭 Spring 应用上下文
        context.close();
    }

    /**
     * [ { "context": "org.springframework.context.annotation.AnnotationConfigApplicationContext@7f690630", "parent": null, "beans": [ { "bean": "liveBeanViewDemo", "aliases": [], "scope": "singleton", "type": "org.geekbang.thinking.in.spring.application.context.lifecycle.LiveBeanViewDemo", "resource": "null", "dependencies": [] }] }]
     */
}
