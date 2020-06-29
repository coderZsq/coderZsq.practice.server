package com.sq.resume.service.impl;

import com.sq.resume.bean.Education;
import com.sq.resume.dao.BaseDao;
import com.sq.resume.dao.impl.EducationDaoImpl;
import com.sq.resume.service.EducationService;

public class EducationServiceImpl extends BaseServiceImpl<Education> implements EducationService {
    @Override
    protected BaseDao<Education> dao() {
        return new EducationDaoImpl();
    }
}
