package com.sq;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import com.sq.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SkillTest {
    private ApplicationContext ctx;
    private SkillService skillService;

    @Before
    public void before() {
        ctx = new AnnotationConfigApplicationContext("com.sq");
        skillService = ctx.getBean("skillService", SkillService.class);
    }

    @Test
    public void save() throws Exception {
        skillService.test(null);
    }
}
