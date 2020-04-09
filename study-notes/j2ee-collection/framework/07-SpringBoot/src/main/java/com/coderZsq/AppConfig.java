package com.coderZsq;

import com.coderZsq.domain.DataSource;
import com.coderZsq.domain.Nanny;
import com.coderZsq.domain.xml.GirlFriend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * 创建bean的方式有两种
 * 如果是bean是我们自定义的bean, 推荐使用ComponentScan导入
 * 如果是第三方框架(依赖的jar包)里面的bean, 推荐使用@Bean注解导入
 */
@Configuration // 配置类 ==> application.xml
@ComponentScan(basePackages = "com.coderZsq.domain.anno") // context:component-scan 如果不写参数, 默认情况扫描当前包及其子包
@Import({DaoConfig.class, ServiceConfig.class, ControllerConfig.class}) // 导入其他的配置类
@ImportResource(locations = "otherBean.xml")
@PropertySource("classpath:db.properties") // 加载属性文件
public class AppConfig {
    @Value("jdbc.username") // 获取到对应的值, 并且设置给属性
    private String username;
    @Value("jdbc.password")
    private String password;
    @Value("jdbc.url")
    private String url;
    @Value("jdbc.driverClassName")
    private String driverClassName;

    @Bean
    public Nanny rongmomo() {
        System.out.println("1234=================");
        return new Nanny();
    }

    // 实例化比较对象
    // @Bean
    // public ConditionOnMissBean conditionOnMissBean() {
    //     ConditionOnMissBean bean = new ConditionOnMissBean();
    //     bean.setBeanName("rongmomo");
    //     return bean;
    // }

    // 定义一个bean对象
    // <bean id="lucy" name="lucy" class="com.coderZsq.domain.xml.GirlFriend"/>
    @Bean(initMethod = "init", destroyMethod = "destroy") // 定义一个bean对象
    @Scope("singleton")
    @Conditional(ConditionOnMissBean.class) // 在创建GirlFriend 容器中必须要有Nanny, 否则就不需要帮我实例化对象
    public GirlFriend lucy(Nanny rongmomo) { // 推荐
        GirlFriend lucy = new GirlFriend();
        lucy.setAge(19);
        // lucy.setNanny(rongmomo()); // 不推荐
        lucy.setNanny(rongmomo);
        return lucy;
    }

    @Bean
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);
        ds.setDriverClassName(driverClassName);
        return ds;
    }
}
