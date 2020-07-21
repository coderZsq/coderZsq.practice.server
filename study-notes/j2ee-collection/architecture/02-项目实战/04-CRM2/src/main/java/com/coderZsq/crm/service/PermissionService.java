package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.Permission;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Permission)表服务接口
 *
 * @author makejava
 * @since 2020-03-20 14:10:40
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<Permission> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<Permission> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 重新加载权限数据
     */
    void reload();
}