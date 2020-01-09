package com.coderZsq.rbac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.common.BizException;
import com.coderZsq.rbac.domain.Employee;
import com.coderZsq.rbac.mapper.EmployeeMapper;
import com.coderZsq.rbac.query.EmployeeQueryObject;
import com.coderZsq.rbac.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Override
    public void delete(Long id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Employee employee,Long[] ids) {
        //1 插入员工数据
        employeeMapper.insert(employee);
        //2 在关联表插入数据
        if(ids!=null && ids.length > 0){
            for (Long id : ids) {
                employeeMapper.insertRelation(employee.getId(),id);
            }
        }
    }

    @Override
    public void update(Employee employee,Long[] ids) {
        //1 删除管理表
        employeeMapper.deleteRelation(employee.getId());
        employeeMapper.updateByPrimaryKey(employee);
        if(ids!=null && ids.length > 0){
            for (Long id : ids) {
                employeeMapper.insertRelation(employee.getId(),id);
            }
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> queryAll() {
        return employeeMapper.selectAll();
    }

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public PageInfo<Employee> query(EmployeeQueryObject queryObject) {
        PageHelper.startPage(queryObject.getCurrentPage(), queryObject.getPageSize());
        List<Employee> employees = employeeMapper.query(queryObject);
        return new PageInfo<>(employees);
    }

    @Override
    public Employee login(String username, String password) {
        Employee user = employeeMapper.selectByUsernameAndPassword(username,password);
        if(user==null){
            throw  new BizException(300001,"用户名或者密码错误");
        }
        return user;
    }

    @Override
    public void batchDelete(Long[] ids) {
        employeeMapper.batchDelete(ids);
    }
}
