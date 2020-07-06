package com.sq.resume.dao.impl;

import com.sq.resume.bean.Project;
import com.sq.resume.dao.ProjectDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl extends BaseDaoImpl<Project> implements ProjectDao {
    @Override
    public boolean save(Project bean) {
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
    public Project get(Integer id) {
        String sql = "SELECT id, created_time, name, image, intro FROM award WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Project.class), id);
    }

    @Override
    public List<Project> list() {
        String sql = "SELECT id, created_time, name, image, intro FROM award";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Project.class));
    }
}
