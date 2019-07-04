package com.coderZsq.example.dao.impl;

import com.coderZsq.example.dao.IEmployeeDAO;
import com.coderZsq.example.domain.Employee;
import com.coderZsq.example.query.EmployeeQueryObject;
import com.coderZsq.example.query.PageResult;
import com.coderZsq.example.util.BeanHandler;
import com.coderZsq.example.util.BeanListHandler;
import com.coderZsq.example.util.IResultSetHandler;
import com.coderZsq.example.util.JdbcTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {
    @Override
    public void save(Employee e) {
        JdbcTemplate.update("INSERT INTO t_employee(name,password,salary) values (?,?,?)", e.getName(), e.getPassword(), e.getSalary());
    }

    @Override
    public void update(Employee e) {
        JdbcTemplate.update("UPDATE t_employee SET name = ?, password = ?, salary = ? WHERE id = ?", e.getName(), e.getPassword(), e.getSalary(), e.getId());
    }

    @Override
    public void delete(Employee e) {
        JdbcTemplate.update("DELETE FROM t_employee WHERE id = ?", e.getId());
    }

    @Override
    public Employee get(Long id) {
        return JdbcTemplate.query("SELECT * FROM t_employee WHERE id = ?", new BeanHandler<>(Employee.class), id);
    }

    @Override
    public List<Employee> listAll() {
        return JdbcTemplate.query("SELECT * FROM t_employee", new BeanListHandler<>(Employee.class));
    }

    @Override
    public List<Employee> query1(EmployeeQueryObject queryObject) {
        String sql = "SELECT * FROM t_employee" + queryObject.getQuery();
        System.out.println("SQL = " + sql);
        System.out.println("params = " + queryObject.getParameters());
        return JdbcTemplate.query(sql, new BeanListHandler<>(Employee.class), queryObject.getParameters().toArray());
    }

    @Override
    public PageResult query(EmployeeQueryObject queryObject) {
        String queryString = queryObject.getQuery();
        // 查询结果总数
        String countSql = "SELECT COUNT(*) FROM t_employee" + queryString;
        int totalCount = JdbcTemplate.query(countSql, new IResultSetHandler<Long>() {
            @Override
            public Long handle(ResultSet resultSet) throws Exception {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
                return 0L;
            }
        }, queryObject.getParameters().toArray()).intValue();
        if (totalCount == 0) {
            return PageResult.empty(queryObject.getPageSize());
        }
        // 查询结果集
        String resultSql = "SELECT * FROM t_employee" + queryString + " LIMIT ?, ?";
        queryObject.getParameters().add((queryObject.getCurrentPage() - 1) * queryObject.getPageSize());
        queryObject.getParameters().add(queryObject.getPageSize());
        List result = JdbcTemplate.query(resultSql, new BeanListHandler<>(Employee.class), queryObject.getParameters().toArray());
        return new PageResult(result, totalCount, queryObject.getCurrentPage(), queryObject.getPageSize());
    }
}
