package com.coderZsq.rbac.service;

import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.query.QueryObject;

import java.util.List;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
public interface IPermissionService {

    public PageInfo<Permission> query(QueryObject queryObject);

    void delete(Long id);

    List<Permission> queryAll();

    List<Permission> queryByRoleId(Long roleId);

    void reload();

}
