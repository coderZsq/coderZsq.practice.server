package com.sq.service.impl;

import com.sq.dao.SkillDao;
import com.sq.domain.Skill;
import com.sq.service.SkillService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService, ApplicationContextAware {
    @Autowired
    private SkillDao dao;

    @Override
    public boolean save(Skill skill) {
        Integer id = skill.getId();
        if (id == null || id < 1) {
            return dao.save(skill);
        }
        return dao.update(skill);
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Override
    public List<Skill> list() {
        return dao.list();
    }

    @Override
    public Skill get(Integer id) {
        return dao.get(id);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
    }
}
