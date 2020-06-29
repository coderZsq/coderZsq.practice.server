package com.sq.resume.service.impl;

import com.sq.resume.bean.Website;
import com.sq.resume.dao.BaseDao;
import com.sq.resume.dao.impl.WebsiteDaoImpl;
import com.sq.resume.service.WebsiteService;

public class WebsiteServiceImpl extends BaseServiceImpl<Website> implements WebsiteService {
    @Override
    protected BaseDao<Website> dao() {
        return new WebsiteDaoImpl();
    }
}
