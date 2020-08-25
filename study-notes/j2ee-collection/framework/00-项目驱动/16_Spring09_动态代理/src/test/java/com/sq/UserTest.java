package com.sq;

import com.sq.service.PersonService;
import com.sq.service.UserService;
import com.sq.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 某些类的某些方法才需要去增加附加代码
public class UserTest {
    @Test
    public void test4() {
        // 创建容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // SkillService skillService = ctx.getBean("skillService", SkillService.class);
        // skillService.save(null);

        UserService userService = ctx.getBean("userService", UserService.class);
        userService.login(null, null);
        userService.register(null, null);

        // PersonService personService = ctx.getBean("personService", PersonService.class);
        // personService.run();
        //
        // Person person = ctx.getBean("person", Person.class);
        // person.eat();
    }

    @Test
    public void test3() {
        // 创建容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 目标对象
        UserService userService = ctx.getBean("userService", UserService.class);
        userService.login(null, null);

        PersonService personService = ctx.getBean("personService", PersonService.class);
        personService.run();
    }

    @Test
    public void test2() {
        // 创建容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 目标对象
        PersonService target = ctx.getBean("personService", PersonService.class);

        // 代理对象
        PersonService personService = (PersonService) Proxy.newProxyInstance(
                getClass().getClassLoader(), // 类加载器
                target.getClass().getInterfaces(), // 代理类需要实现的接口 (目标类的接口)
                (Object proxy, Method method, Object[] args) -> { // 附加代码 (代理类的具体实现)
                    // proxy: 代理对象
                    // method: 目标方法
                    // args: 目标方法的参数
                    System.out.println("proxy - 1");

                    // 调用目标对象的目标方法 (核心业务代码)
                    Object result = method.invoke(target, args);

                    System.out.println("proxy - 2");
                    return result;
                });

        personService.run();

        // 关闭容器
        ctx.close();
    }

    @Test
    public void test() {
        // 创建容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 目标对象
        UserServiceImpl target = ctx.getBean("userService", UserServiceImpl.class);

        // 代理对象
        UserService userService = (UserService) Proxy.newProxyInstance(
                getClass().getClassLoader(), // 类加载器
                target.getClass().getInterfaces(), // 代理类需要实现的接口 (目标类的接口)
                (Object proxy, Method method, Object[] args) -> { // 附加代码 (代理类的具体实现)
                    // proxy: 代理对象
                    // method: 目标方法
                    // args: 目标方法的参数
                    System.out.println("proxy - 1");

                    // 调用目标对象的目标方法 (核心业务代码)
                    Object result = method.invoke(target, args);

                    System.out.println("proxy - 2");
                    return result;
                });

        userService.login("123", "456");
        userService.register("123", "456");

        // 关闭容器
        ctx.close();

        // SkillService skillService = ctx.getBean("skillService", SkillService.class);
        // skillService.save(null);
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
