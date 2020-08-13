package com.sq.dao.impl;

import com.sq.bean.Skill;
import com.sq.dao.SkillDao;
import com.sq.util.MyBatises;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SkillDaoImpl implements SkillDao {
    @Override
    public boolean save(Skill skill) {
        try (SqlSession session = MyBatises.openSession(true)) {
            return session.insert("skill.save", skill) > 0;
        }
    }

    @Override
    public boolean update(Skill skill) {
        try (SqlSession session = MyBatises.openSession(true)) {
            return session.update("skill.update", skill) > 0;
        }
    }

    @Override
    public boolean remove(Integer id) {
        try (SqlSession session = MyBatises.openSession(true)) {
            return session.delete("skill.remove", id) > 0;
        }
    }

    @Override
    public Skill get(Integer id) {
        try (SqlSession session = MyBatises.openSession()) {
            return session.selectOne("skill.get", id);
        }
    }

    @Override
    public List<Skill> list() {
        try (SqlSession session = MyBatises.openSession()) {
            return session.selectList("skill.list");
        }
    }
}
