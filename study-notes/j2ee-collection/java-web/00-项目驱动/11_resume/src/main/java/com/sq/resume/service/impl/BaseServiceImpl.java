package com.sq.resume.service.impl;

import com.sq.resume.dao.BaseDao;
import com.sq.resume.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> dao = dao();

    protected abstract BaseDao<T> dao();

    /**
     * 删除
     */
    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Override
    public boolean remove(List<Integer> ids) {
        return dao.remove(ids);
    }

    /**
     * 添加或更新
     */
    @Override
    public boolean save(T bean) {
        return dao.save(bean);
    }

    /**
     * 获取单个对象
     */
    @Override
    public T get(Integer id) {
        return dao.get(id);
    }

    /**
     * 获取多个对象
     */
    @Override
    public List<T> list() {
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
