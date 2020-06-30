package com.sq.resume.service.impl;

import com.sq.resume.bean.Award;
import com.sq.resume.dao.BaseDao;
import com.sq.resume.dao.impl.AwardDaoImpl;
import com.sq.resume.service.AwardService;

public class AwardServiceImpl extends BaseServiceImpl<Award> implements AwardService {
    @Override
    protected BaseDao<Award> dao() {
        return new AwardDaoImpl();
    }
}
