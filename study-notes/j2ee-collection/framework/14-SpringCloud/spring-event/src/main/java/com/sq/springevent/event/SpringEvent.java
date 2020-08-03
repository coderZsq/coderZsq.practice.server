package com.sq.springevent.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextStartedEvent;

import java.io.IOException;

/**
 * 区别:
 * 1 使用的基类不一样 java --> EventObject Spring --> ApplicationEvent
 * 2 对于java中的事件监听: 多事件监听 spring中的事件 单事件监听, SmartApplicationListener 可以完成多个事件的监听
 */
public class SpringEvent {
    public static void main(String[] args) throws IOException {
        // 1 先启动spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(new ApplicationListener<ContextStartedEvent>() { // ApplicationListener 事件监听 ContextStartedEvent
            @Override
            public void onApplicationEvent(ContextStartedEvent event) { // 事件类型 ContextStartedEvent
                System.out.println("事件类型: " + event.getClass()); // 业务处理
                System.out.println("监听容器的start方法触发");
            }
        });
        context.refresh(); // 启动容器
        context.start(); // 开始方法 --> publishEvent(new ContextStartedEvent(this));
        context.stop(); // 停止容器
        context.close(); // 关闭容器
        System.in.read();
    }
}
