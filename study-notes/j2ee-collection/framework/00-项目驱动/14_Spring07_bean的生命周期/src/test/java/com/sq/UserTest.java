package com.sq;

import com.sq.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void test() {
        // 创建容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService service = ctx.getBean("userService", UserService.class);
        service.login("123", "456");

        // 关闭容器
        ctx.close();
    }

    /*
      # 构造方法
      01 - UserServiceImpl

      # 属性注入
      02 - setAge - 20

      # 让你知道一下bean的名字 (id, name)
      03 - BeanNameAware - setBeanName - userService

      # 让你知道一下你在哪个容器里面
      04 - ApplicationContextAware - setApplicationContext - org.springframework.context.support.ClassPathXmlApplicationContext@66133adc, started on Tue Aug 25 12:11:06 CST 2020

      # 初始化方法调用之前调用
      05 - BeanPostProcessor - postProcessBeforeInitialization - userService

      # 构造, 注入完毕之后调用 ① (初始化, 加载资源)
      06 - InitializingBean - afterPropertiesSet

      # 构造, 注入完毕之后调用 ② (初始化, 加载资源)
      07 - init-method

      # 初始化方法调用之后调用
      08 - BeanPostProcessor - postProcessAfterInitialization - userService

      # 业务方法
      09 - UserServiceImpl - login - 123_456

      # 销毁之前调用 ① (释放资源)
      10 - DisposableBean - destroy

      # 销毁之前调用 ② (释放资源)
      11 - destroy-method
     */
}
