package com.coderZsq.rbac.service;

import com.coderZsq.rbac.domain.Role;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.qo.QueryObject;

import java.util.List;

public interface IRoleService {
    //添加和更新
    public void saveOrUpdate(Role role, Long[] permissionIds);

    //删除
    void delete(Long id);


    //根据id查
    Role getById(Long id);

    //查所有
    List<Role> getAllList();

    PageResult<Role> selectByQuery(QueryObject qo);
}
