package com.coderZsq.crm.mapper;

import com.coderZsq.crm.domain.CustomerTransfer;

import java.util.List;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Customertransfer)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-01 10:17:34
 */
public interface CustomerTransferMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerTransfer queryById(Long id);

    /**
     * 查询所有行数据
     * @return 对象列表
     */
    List<CustomerTransfer> queryAll();
	
	/**
     * 根据查询条件查询指定列表数据
     * @return 对象列表
     */
    List<CustomerTransfer> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param customertransfer 实例对象
     * @return 影响行数
     */
    int insert(CustomerTransfer customertransfer);

    /**
     * 修改数据
     *
     * @param customertransfer 实例对象
     * @return 影响行数
     */
    int update(CustomerTransfer customertransfer);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}