package com.coderZsq.rbac.service;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.query.EmployeeQueryObject;

import java.util.List;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public interface IEmployeeService {


    /**
     * 根据id删除指定员工
     * @param id
     */
    public void delete(Long id);

    /**
     * 新增员工
     * @param employee
     */
    public void insert(Employee employee,Long[] ids);

    /**
     * 根据员工id修改员工
     * @param employee
     */
    public void update(Employee employee,Long[] ids);

    /**
     * 根据员工Id获取员工
     * @param id
     * @return
     */
    public Employee get(Long id);

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> queryAll();

    /**
     * 员工列表界面分页查询
     * @param queryObject
     * @return
     */
    public PageInfo<Employee> query(EmployeeQueryObject queryObject);

    Employee login(String username, String password);

    void batchDelete(Long[] ids);
}
