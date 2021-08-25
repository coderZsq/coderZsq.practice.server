package org.geekbang.thinking.in.spring.event;

import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 Spring 事件示例
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 1. 添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());

        // 2. 启动 Spring 应用上下文
        context.refresh();

        // 3. 发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello, World"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }
}
