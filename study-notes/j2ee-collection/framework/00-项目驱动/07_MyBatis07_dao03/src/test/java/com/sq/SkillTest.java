package com.sq;

import com.sq.bean.Skill;
import com.sq.dao.SkillDao;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SkillTest {
    private SkillDao dao;

    @Test
    public void get() {
        try (SqlSession session = MyBatises.openSession()) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            System.out.println(dao.get(2));
        }
    }

    @Test
    public void list() {
        try (SqlSession session = MyBatises.openSession()) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            System.out.println(dao.list());
        }
    }

    @Test
    public void list2() {
        try (SqlSession session = MyBatises.openSession()) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            List<Skill> skills = dao.listByStartAndSize(0, 10);
            for (Skill skill : skills) {
                System.out.println(skill);
            }
        }
    }

    @Test
    public void save() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            Assert.assertTrue(dao.save(new Skill("888", 100)));
        }
    }

    @Test
    public void update() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            Skill skill = new Skill("666", 99);
            skill.setId(2);
            Assert.assertTrue(dao.update(skill));
        }
    }

    @Test
    public void remove() {
        try (SqlSession session = MyBatises.openSession(true)) {
            // 生成SkillDao的代理对象
            dao = session.getMapper(SkillDao.class);
            Assert.assertTrue(dao.remove(25));
        }
    }
}

