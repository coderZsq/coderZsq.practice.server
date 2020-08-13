package com.sq.dao;

import com.sq.bean.Skill;

import java.util.List;

public interface SkillDao {
    boolean save(Skill skill);
    boolean update(Skill skill);
    boolean remove(Integer id);
    Skill get(Integer id);
    List<Skill> list();
}
