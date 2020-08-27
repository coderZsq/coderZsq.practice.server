package com.sq.service;

import com.sq.domain.Skill;

import java.util.List;

public interface SkillService {
    List<Skill> list();

    boolean save(Skill skill);

    boolean update(Skill skill);

    void test(Skill skill) throws Exception;
}
