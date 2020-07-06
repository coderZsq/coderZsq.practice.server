package com.sq.resume.dao.impl;

import com.sq.resume.bean.#0#;
import com.sq.resume.dao.#0#Dao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class #0#DaoImpl extends BaseDaoImpl<#0#> implements #0#Dao {
    @Override
    public boolean save(#0# bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();

        String sql;
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO award(name, image, intro) VALUES(?, ?, ?)";
        } else {
            sql = "UPDATE award SET name = ?, image = ?, intro = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    @Override
    public #0# get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(#0#.class), id);
    }

    @Override
    public List<#0#> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(sql, new BeanPropertyRowMapper<>(#0#.class));
    }
}
