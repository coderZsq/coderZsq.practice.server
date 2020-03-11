package com.hesj.rbac.service;

import com.hesj.rbac.domain.Employee;
import com.hesj.rbac.qo.EmployeeQueryObject;
import com.hesj.rbac.qo.PageResult;

import java.util.List;

public interface IEmployeeService {
    //添加和更新
    public void saveOrUpdate(Employee employee, Long[] roleIds);

    //删除
    void delete(Long id);


    //根据id查
    Employee getById(Long id);

    //查所有
    List<Employee> getAllList();

    PageResult<Employee> selectByQuery(EmployeeQueryObject qo);

    Employee selectByNameAndPassword(String username, String password);
}
