package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.SystemDictionary;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Systemdictionary)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 11:28:46
 */
public interface SystemDictionaryService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemDictionary queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<SystemDictionary> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<SystemDictionary> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param systemdictionary 实例对象
     * @return 实例对象
     */
    SystemDictionary insert(SystemDictionary systemdictionary);

    /**
     * 修改数据
     * @param systemdictionary 实例对象
     * @return 实例对象
     */
    SystemDictionary update(SystemDictionary systemdictionary);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}