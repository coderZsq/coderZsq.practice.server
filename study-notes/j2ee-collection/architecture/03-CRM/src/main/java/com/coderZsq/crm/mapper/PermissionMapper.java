package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Permission)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-20 14:10:40
 */
public interface PermissionMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<Permission> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<Permission> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<String> queryExp();

    List<Permission> queryByRoleId(Long id);

    List<String> querySnByEmpId(Long id);
}