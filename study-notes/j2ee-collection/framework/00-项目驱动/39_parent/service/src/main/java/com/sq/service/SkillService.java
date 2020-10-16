package com.sq.service;

import com.sq.domain.Skill;

import java.util.List;

public interface SkillService {
    boolean save(Skill skill);

    boolean remove(Integer id);

    List<Skill> list();

    Skill get(Integer id);
}
