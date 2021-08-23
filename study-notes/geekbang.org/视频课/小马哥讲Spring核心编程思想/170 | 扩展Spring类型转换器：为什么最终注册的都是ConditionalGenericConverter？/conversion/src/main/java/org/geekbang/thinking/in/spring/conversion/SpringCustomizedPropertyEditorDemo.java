package org.geekbang.thinking.in.spring.conversion;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Spring 自定义 {@link PropertyEditor}
 */
public class SpringCustomizedPropertyEditorDemo {
    public static void main(String[] args) {
        // 创建并且启动 BeanFactory 容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        User user = applicationContext.getBean("user", User.class);

        System.out.println(user);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
