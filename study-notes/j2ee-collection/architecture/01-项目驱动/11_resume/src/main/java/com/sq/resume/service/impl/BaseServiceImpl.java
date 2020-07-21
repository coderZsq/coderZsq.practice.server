package com.sq.resume.service.impl;

import com.sq.resume.dao.BaseDao;
import com.sq.resume.service.BaseService;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    protected BaseDao<T> dao = newDao();

    protected BaseDao<T> newDao() {
        // com.sq.resume.service.impl.WebsiteServiceImpl
        // com.sq.resume.dao.impl.WebsiteDaoImpl
        try {
            String clsName = getClass().getName()
                    .replace(".service.", ".dao.")
                    .replace("Service", "Dao");
            return (BaseDao<T>) Class.forName(clsName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

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
