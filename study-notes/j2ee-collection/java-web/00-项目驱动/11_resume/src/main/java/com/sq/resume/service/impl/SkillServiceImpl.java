package com.sq.resume.service.impl;

import com.sq.resume.bean.Skill;
import com.sq.resume.dao.BaseDao;
import com.sq.resume.dao.impl.SkillDaoImpl;
import com.sq.resume.service.SkillService;

public class SkillServiceImpl extends BaseServiceImpl<Skill> implements SkillService {
    @Override
    protected BaseDao<Skill> dao() {
        return new SkillDaoImpl();
    }
}
