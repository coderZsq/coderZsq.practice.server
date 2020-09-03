package com.sq;

import com.sq.cfg.AppConfig;
import com.sq.domain.Student;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Student student = ctx.getBean("student", Student.class);
        student.run();
        ctx.close();
    }
}
