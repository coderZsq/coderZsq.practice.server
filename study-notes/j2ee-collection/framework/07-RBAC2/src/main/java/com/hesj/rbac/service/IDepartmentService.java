package com.hesj.rbac.service;

import com.hesj.rbac.domain.Department;
import com.hesj.rbac.qo.PageResult;
import com.hesj.rbac.qo.QueryObject;

import java.util.List;

public interface IDepartmentService {
    //添加和更新
    public void saveOrUpdate(Department department);

    //删除
    void delete(Long id);


    //根据id查
    Department getById(Long id);

    //查所有
    List<Department> getAllList();

    PageResult<Department> selectByQuery(QueryObject qo);
}
