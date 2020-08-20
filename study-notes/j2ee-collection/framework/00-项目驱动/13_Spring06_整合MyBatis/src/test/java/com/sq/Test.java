package com.sq;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    @org.junit.Test
    public void list() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SkillDao dao = ctx.getBean("skillDao", SkillDao.class);
        List<Skill> skills = dao.list();
        System.out.println(skills);
    }
}
