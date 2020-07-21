package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.SystemDictionary;

import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Systemdictionary)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 11:28:46
 */
public interface SystemDictionaryMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemDictionary queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<SystemDictionary> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<SystemDictionary> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param systemdictionary 实例对象
     * @return 影响行数
     */
    int insert(SystemDictionary systemdictionary);

    /**
     * 修改数据
     *
     * @param systemdictionary 实例对象
     * @return 影响行数
     */
    int update(SystemDictionary systemdictionary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}