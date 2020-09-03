package com.sq;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SkillTest {
    private ApplicationContext ctx;
    private SkillDao skillDao;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        skillDao = ctx.getBean("skillDao", SkillDao.class);
    }

    @Test
    public void list() {
        List<Skill> skills = skillDao.list();
        System.out.println(skills);
    }

    @Test
    public void save() {
        System.out.println(skillDao.save(new Skill("123", 456)));
    }
}
