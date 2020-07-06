package com.sq.resume.dao.impl;

import com.sq.resume.bean.Website;
import com.sq.resume.dao.WebsiteDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class WebsiteDaoImpl extends BaseDaoImpl<Website> implements WebsiteDao {
    /**
     * 添加或更新
     */
    @Override
    public boolean save(Website bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();
        args.add(bean.getFooter());
        String sql;
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO website(footer) VALUES(?)";
        } else {
            sql = "UPDATE website SET footer = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    /**
     * 获取单个对象
     */
    @Override
    public Website get(Integer id) {
        return null;
    }

    /**
     * 获取多个对象
     */
    @Override
    public List<Website> list() {
        String sql = "SELECT id, created_time, footer FROM website";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Website.class));
    }
}
