package com.sq.service.impl;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import com.sq.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("skillService")
@Transactional
public class SkillServiceImpl implements SkillService {
    private SkillDao dao;

    @Autowired
    public void setDao(SkillDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
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

        throw new RuntimeException();
    }
}
