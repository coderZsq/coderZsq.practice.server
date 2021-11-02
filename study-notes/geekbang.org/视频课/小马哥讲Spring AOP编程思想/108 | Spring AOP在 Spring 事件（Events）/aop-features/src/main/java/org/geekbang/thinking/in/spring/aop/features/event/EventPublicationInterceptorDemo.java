package org.geekbang.thinking.in.spring.aop.features.event;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * {@link EventPublicationInterceptor} 示例
 */
@Configuration // Configuration Class
@EnableAspectJAutoProxy
public class EventPublicationInterceptorDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EventPublicationInterceptorDemo.class, Executor.class, StaticExecutor.class);
        // 启动 Spring 应用上下文
        context.refresh();

        // 5. 执行目标方法
        Executor executor = context.getBean(Executor.class);

        StaticExecutor staticExecutor = context.getBean(StaticExecutor.class);

        executor.execute();

        staticExecutor.execute();

        // 关闭 Spring 应用上下文
        context.close();
    }

    // 1. 将 EventPublicationInterceptor 声明为 Spring Bean
    @Bean
    public static EventPublicationInterceptor eventPublicationInterceptor() {
        EventPublicationInterceptor eventPublicationInterceptor = new EventPublicationInterceptor();
        // 关联目标 (自定义) 事件类型 - ExecutedEvent
        eventPublicationInterceptor.setApplicationEventClass(ExecutedEvent.class);
        return eventPublicationInterceptor;
    }

    // 2. 实现 Pointcut
    @Bean
    public static Pointcut pointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "execute".equals(method.getName()) && Executor.class.equals(targetClass);
            }
        };
    }

    // 3. 声明一个 Advisor Bean
    @Bean
    public static PointcutAdvisor pointcutAdvisor(Pointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor) {
        // EventPublicationInterceptor is MethodInterceptor is Advice
        return new DefaultPointcutAdvisor(pointcut, eventPublicationInterceptor);
    }

    // 4. 处理事件 - ExecutedEvent
    @EventListener(ExecutedEvent.class)
    public void executed(ExecutedEvent event) {
        System.out.println("Executed : " + event);
    }
}
