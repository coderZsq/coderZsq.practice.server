package com.sq.service.impl;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import com.sq.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {
    private SkillDao dao;

    public void setDao(SkillDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Skill> list() {
        return dao.list();
    }

    @Override
    public boolean save(Skill skill) {
        return dao.save(skill);
    }

    @Override
    public boolean update(Skill skill) {
        return dao.update(skill);
    }

    @Override
    public void test(Skill skill) throws Exception {
        dao.save(skill);

        throw new Exception();

        // 当前的事务情况
        // update(null);

        // Skill skill = new Skill("777", 777);
        // skill.setId(2);
        // dao.update(skill);
        //
        // System.out.println(10 / 0);
        //
        // dao.save(new Skill("888", 888));
    }
}
