package com.sq.resume.service;

import com.sq.resume.bean.Website;
import com.sq.resume.dao.WebsiteDao;
import com.sq.resume.dao.WebsiteDaoImpl;

import java.util.List;

public class WebsiteServiceImpl implements WebsiteService {
    private WebsiteDao dao = new WebsiteDaoImpl();

    /**
     * 删除
     */
    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Override
    public boolean remove(List<Integer> ids) {
        return false;
    }

    /**
     * 添加或更新
     */
    @Override
    public boolean save(Website website) {
        return dao.save(website);
    }

    /**
     * 获取单个对象
     */
    @Override
    public Website get(Integer id) {
        return dao.get(id);
    }

    /**
     * 获取多个对象
     */
    @Override
    public List<Website> list() {
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
