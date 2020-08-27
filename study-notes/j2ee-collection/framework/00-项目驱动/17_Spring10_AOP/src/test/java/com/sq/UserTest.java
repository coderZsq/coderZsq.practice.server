package com.sq;

import com.sq.service.PersonService;
import com.sq.service.SkillService;
import com.sq.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// 某些类的某些方法才需要去增加附加代码
public class UserTest {
    @Test
    public void test() {
        // 创建容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        SkillService skillService = ctx.getBean("skillService", SkillService.class);
        skillService.save(null);

        UserService userService = ctx.getBean("userService", UserService.class);
        userService.login(null, null);
        userService.register(null);

        PersonService personService = ctx.getBean("personService", PersonService.class);
        personService.run();
    }
}
