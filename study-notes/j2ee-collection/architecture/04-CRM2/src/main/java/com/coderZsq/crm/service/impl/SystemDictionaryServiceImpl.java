package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.SystemDictionary;
import com.coderZsq.crm.mapper.SystemDictionaryMapper;
import com.coderZsq.crm.service.SystemDictionaryService;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Systemdictionary)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 11:28:46
 */
@Service("systemDictionaryService")
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

    @Autowired
    private SystemDictionaryMapper systemdictionaryMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SystemDictionary queryById(Long id) {
        return this.systemdictionaryMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<SystemDictionary> queryAll() {
        return this.systemdictionaryMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<SystemDictionary> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<SystemDictionary> list = systemdictionaryMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param systemdictionary 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDictionary insert(SystemDictionary systemdictionary) {
        this.systemdictionaryMapper.insert(systemdictionary);
        return systemdictionary;
    }

    /**
     * 修改数据
     *
     * @param systemdictionary 实例对象
     * @return 实例对象
     */
    @Override
    public SystemDictionary update(SystemDictionary systemdictionary) {
        this.systemdictionaryMapper.update(systemdictionary);
        return this.queryById(systemdictionary.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.systemdictionaryMapper.deleteById(id) > 0;
    }
}