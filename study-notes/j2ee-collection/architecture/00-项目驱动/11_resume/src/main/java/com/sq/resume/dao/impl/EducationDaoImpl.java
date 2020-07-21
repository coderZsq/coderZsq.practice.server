package com.sq.resume.dao.impl;

import com.sq.resume.bean.Education;
import com.sq.resume.dao.EducationDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl extends BaseDaoImpl<Education> implements EducationDao {
    /**
     * 添加或更新
     */
    @Override
    public boolean save(Education bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();
        args.add(bean.getName());
        args.add(bean.getType());
        args.add(bean.getIntro());
        args.add(bean.getBeginDay());
        args.add(bean.getEndDay());
        String sql;
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO education(name, type, intro, begin_day, end_day) VALUES(?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE education SET name = ?, type = ?, intro = ?, begin_day = ?, end_day = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    /**
     * 获取单个对象
     */
    @Override
    public Education get(Integer id) {
        String sql = "SELECT id, created_time, name, type, intro, begin_day, end_day FROM education WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Education.class), id);
    }

    /**
     * 获取多个对象
     */
    @Override
    public List<Education> list() {
        String sql = "SELECT id, created_time, name, type, intro, begin_day, end_day FROM education";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Education.class));
    }
}
