package org.geekbang.thinking.in.spring.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * {@link TestPropertySource} 测试示例
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class) // Spring 注解驱动测试注解
@TestPropertySource(
        properties = "user.name = 小马哥", // PropertySource(name=Inlined Test Properties)
        locations = "classpath:/META-INF/test.properties"
)
public class TestPropertySourceTest {

    @Value("${user.name}") // "mercyblitz" Java System Properties
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        assertEquals("小马哥", userName);

        for (PropertySource ps : environment.getPropertySources()) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性: %s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }
}
