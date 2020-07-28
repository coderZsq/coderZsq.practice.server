package com.sq.resume.dao.impl;

import com.sq.resume.bean.Contact;
import com.sq.resume.bean.ContactListParam;
import com.sq.resume.bean.ContactListResult;
import com.sq.resume.dao.ContactDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl extends BaseDaoImpl<Contact> implements ContactDao {
    @Override
    public boolean save(Contact bean) {
        Integer id = bean.getId();
        List<Object> args = new ArrayList<>();
        args.add(bean.getName());
        args.add(bean.getEmail());
        args.add(bean.getComment());
        args.add(bean.getSubject());
        args.add(bean.getAlreadyRead());

        String sql;
        if (id == null || id < 1) { // 添加
            sql = "INSERT INTO contact(name, email, comment, subject, already_read) VALUES(?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE contact SET name = ?, email = ?, comment = ?, subject = ?, already_read = ? WHERE id = ?";
            args.add(id);
        }
        return tpl.update(sql, args.toArray()) > 0;
    }

    @Override
    public Contact get(Integer id) {
        String sql = "SELECT id, created_time, name, email, comment, subject, already_read FROM contact WHERE id = ?";
        return tpl.queryForObject(sql, new BeanPropertyRowMapper<>(Contact.class), id);
    }

    @Override
    public List<Contact> list() {
        String sql = "SELECT id, created_time, name, email, comment, subject, already_read FROM contact";
        return tpl.query(sql, new BeanPropertyRowMapper<>(Contact.class));
    }

    @Override
    public ContactListResult list(ContactListParam param) {
        ContactListResult result = new ContactListResult();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, created_time, name, email, comment, subject, already_read FROM contact WHERE 1 = 1 ");
        // 参数
        List<Object> args = new ArrayList<>();

        // 条件
        StringBuilder condition = new StringBuilder();
        if (param.getBeginDay() != null) { // 开始时间
            condition.append("AND created_time >= ? ");
            args.add(param.getBeginDay());
            result.setBeginDay(param.getBeginDay());
        }

        if (param.getEndDay() != null) { // 结束时间
            condition.append("AND created_time <= ? ");
            args.add(param.getEndDay());
            result.setEndDay(param.getEndDay());
        }

        String keyword = param.getKeyword();
        if (keyword != null && keyword.length() > 0) { // 关键字
            result.setKeyword(keyword);
            condition.append("AND (name LIKE ? OR email LIKE ? OR subject LIKE ? OR comment LIKE ?) ");
            keyword = "%" + keyword + "%";
            args.add(keyword);
            args.add(keyword);
            args.add(keyword);
            args.add(keyword);
        }

        Integer read = param.getAlreadyRead();
        if (read != null && read < ContactListParam.READ_ALL) { // 阅读状态
            condition.append("AND already_read = ? ");
            args.add(read);
            result.setAlreadyRead(read);
        }

        Integer pageSize = param.getPageSize();
        if (pageSize == null) {
            pageSize = 10;
        }

        /*
         总数量: 101
         每一页显示20条
         总页数 = (总数量 + 每页的数量 + 1) / 每页的数量
         */
        String countSql = "SELECT COUNT(*) FROM contact WHERE 1 = 1 " + condition;
        Integer totalCount = tpl.queryForObject(countSql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt(1);
            }
        }, args.toArray());
        result.setTotalPages((totalCount + pageSize - 1) / pageSize);
        result.setTotalCount(totalCount);

        // 分页
        sql.append(condition);
        sql.append("LIMIT ?, ?");
        Integer pageNo = param.getPageNo();
        if (pageNo == null) {
            pageNo = 1;
        }
        args.add((pageNo - 1) * pageSize);
        args.add(pageSize);
        result.setPageNo(pageNo);
        result.setPageSize(pageSize);

        List<Contact> contacts = tpl.query(sql.toString(), new BeanPropertyRowMapper<>(Contact.class), args.toArray());
        result.setContacts(contacts);

        return result;
    }
}
