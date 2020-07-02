package com.sq.resume.service.impl;

import com.sq.resume.bean.Award;
import com.sq.resume.dao.BaseDao;
import com.sq.resume.dao.impl.AwardDaoImpl;
import com.sq.resume.service.AwardService;

import java.util.List;

public class AwardServiceImpl extends BaseServiceImpl<Award> implements AwardService {
    @Override
    protected BaseDao<Award> dao() {
        return new AwardDaoImpl();
    }

    @Override
    public boolean remove(List<Integer> ids) {
        // 删除图片文件
        return super.remove(ids);
    }

    @Override
    public boolean remove(Integer id) {
        // 删除图片文件
        return super.remove(id);
    }
}
