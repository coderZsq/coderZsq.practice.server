package com.sq.resume.service;

import com.sq.resume.bean.Website;

import java.util.List;

public interface WebsiteService {
    /**
     * 删除
     */
    boolean remove(Integer id);

    /**
     * 删除
     */
    boolean remove(List<Integer> ids);

    /**
     * 添加或更新
     */
    boolean save(Website website);

    /**
     * 获取单个对象
     */
    Website get(Integer id);

    /**
     * 获取多个对象
     */
    List<Website> list();

    /**
     * 获取统计值
     */
    int count();
}
