package com.coderZsq.crm.service;

import com.coderZsq.crm.domain.CustomerTransfer;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.coderZsq.crm.query.QueryObject;

/**
 * (Customertransfer)表服务接口
 *
 * @author makejava
 * @since 2020-04-01 10:17:34
 */
public interface CustomerTransferService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CustomerTransfer queryById(Long id);

    /**
     * 查询所有数据
     * @return 对象列表
     */
    List<CustomerTransfer> queryAll();
	
    /**
     * 查询指定列表数据
     * @return 对象列表
     */
    PageInfo<CustomerTransfer> query(QueryObject qo);

    /**
     * 新增数据
     *
     * @param customertransfer 实例对象
     * @return 实例对象
     */
    CustomerTransfer insert(CustomerTransfer customertransfer);

    /**
     * 修改数据
     * @param customertransfer 实例对象
     * @return 实例对象
     */
    CustomerTransfer update(CustomerTransfer customertransfer);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    void absorb(CustomerTransfer customertransfer);
}