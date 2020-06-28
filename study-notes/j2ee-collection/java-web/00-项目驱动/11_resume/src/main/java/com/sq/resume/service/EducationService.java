package com.sq.resume.service;

import com.sq.resume.bean.Education;

import java.util.List;

public interface EducationService {
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
    boolean save(Education education);

    /**
     * 获取单个对象
     */
    Education get(Integer id);

    /**
     * 获取多个对象
     */
    List<Education> list();

    /**
     * 获取统计值
     */
    int count();
}
