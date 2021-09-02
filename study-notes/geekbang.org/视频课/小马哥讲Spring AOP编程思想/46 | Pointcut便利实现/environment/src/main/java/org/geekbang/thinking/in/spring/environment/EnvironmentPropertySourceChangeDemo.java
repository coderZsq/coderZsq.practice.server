package org.geekbang.thinking.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Environment} 配置属性源变更示例
 *
 * @see Environment
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}") // 不具备动态更新能力
    private String userName;

    // PropertySource("first-property-source") { user.name = 小马哥}
    // PropertySource( Java System Properties) { user.name = mercyblitz }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(EnvironmentPropertySourceChangeDemo.class);

        // 在 Spring 应用上下文启动前, 调整 Environment 中的 PropertySource
        ConfigurableEnvironment environment = context.getEnvironment();
        // 获取 MutablePropertySources 对象
        MutablePropertySources propertySources = environment.getPropertySources();
        // 动态地插入 PropertySource 到 PropertySources 中
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "小马哥");
        MapPropertySource propertySource = new MapPropertySource("first-property-source", source);
        propertySources.addFirst(propertySource);

        // 启动 Spring 应用上下文
        context.refresh();

        source.put("user.name", "007");

        EnvironmentPropertySourceChangeDemo environmentPropertySourceChangeDemo = context.getBean(EnvironmentPropertySourceChangeDemo.class);

        System.out.println(environmentPropertySourceChangeDemo.userName);

        for (PropertySource ps : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性: %s\n", ps.getName(), ps.getProperty("user.name"));
        }

        // 关闭 Spring 应用上下文
        context.close();
    }
}
