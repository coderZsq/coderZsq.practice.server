package com.hesj.demo;

import com.hesj.demo.domain.Department;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public class App {

    @Test
    public void testBean() throws Exception {
        //ApplicationContext IOC容器
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Department department = (Department) ctx.getBean("department"); //根据名字获取
        System.out.println(department);
        Department bean2 = ctx.getBean(Department.class);//根据对象类型获取bean对象
        System.out.println(bean2);
        ctx.close();
    }

    @Test
    public void testAnno() throws Exception {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IDepartmentService bean = ctx.getBean(IDepartmentService.class);
        bean.get(1L);
        ctx.close();
    }
}
