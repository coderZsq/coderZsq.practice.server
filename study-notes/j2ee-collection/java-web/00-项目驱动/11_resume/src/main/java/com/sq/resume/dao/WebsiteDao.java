package com.sq.resume.dao;

import com.sq.resume.bean.Website;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class WebsiteDao extends BaseDao {
    /**
     * 删除
     */
    public boolean remove(Integer id) {
        return false;
    }

    /**
     * 添加或更新
     */
    public boolean save(Website website) {
        Integer id = website.getId();
        List<Object> args = new ArrayList<>();
        args.add(website.getFooter());
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
    public Website get(Integer id) {
        return null;
    }

    /**
     * 获取多个对象
     */
    public List<Website> list() {
        String sql = "SELECT id, created_time, footer FROM website";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Website.class));
    }

    /**
     * 获取统计值
     */
    public int count() {
        return 0;
    }
}
