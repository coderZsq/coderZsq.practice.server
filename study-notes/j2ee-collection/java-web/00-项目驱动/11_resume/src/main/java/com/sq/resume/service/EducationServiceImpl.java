package com.sq.resume.service;

import com.sq.resume.bean.Education;
import com.sq.resume.dao.EducationDao;
import com.sq.resume.dao.EducationDaoImpl;

import java.util.List;

public class EducationServiceImpl implements EducationService {
    private EducationDao dao = new EducationDaoImpl();

    /**
     * 删除
     */
    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    /**
     * 删除
     */
    @Override
    public boolean remove(List<Integer> ids) {
        return dao.remove(ids);
    }

    /**
     * 添加或更新
     */
    @Override
    public boolean save(Education education) {
        return dao.save(education);
    }

    /**
     * 获取单个对象
     */
    @Override
    public Education get(Integer id) {
        return dao.get(id);
    }

    /**
     * 获取多个对象
     */
    @Override
    public List<Education> list() {
        return dao.list();
    }

    /**
     * 获取统计值
     */
    @Override
    public int count() {
        return dao.count();
    }
}
