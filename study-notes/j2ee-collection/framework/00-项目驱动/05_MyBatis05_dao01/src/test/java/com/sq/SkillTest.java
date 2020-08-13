package com.sq;

import com.sq.bean.Skill;
import com.sq.dao.SkillDao;
import com.sq.dao.impl.SkillDaoImpl;
import org.junit.Assert;
import org.junit.Test;

public class SkillTest {
    @Test
    public void get() {
        SkillDao dao = new SkillDaoImpl();
        System.out.println(dao.get(2));
    }

    @Test
    public void list() {
        SkillDao dao = new SkillDaoImpl();
        System.out.println(dao.list());
    }

    @Test
    public void save() {
        SkillDao dao = new SkillDaoImpl();
        Assert.assertTrue(dao.save(new Skill("888", 100)));
    }

    @Test
    public void update() {
        SkillDao dao = new SkillDaoImpl();
        Skill skill = new Skill("666", 99);
        skill.setId(24);
        Assert.assertTrue(dao.update(skill));
    }

    @Test
    public void remove() {
        SkillDao dao = new SkillDaoImpl();
        Assert.assertTrue(dao.remove(24));
    }
}

