package org.geekbang.thinking.in.spring.environment;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link PropertyPlaceholderConfigurer} 处理属性占位符示例
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {

        // 创建并且启动 Spring 应用上下文
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user);

        // 关闭 Spring 应用上下文
        context.close();
    }
}
