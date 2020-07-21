package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.CustomerTraceHistory;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.mapper.CustomerTraceHistoryMapper;
import com.coderZsq.crm.service.CustomerTraceHistoryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * (Customertracehistory)表服务实现类
 *
 * @author makejava
 * @since 2020-03-31 17:08:38
 */
@Service("customertracehistoryService")
public class CustomerTraceHistoryServiceImpl implements CustomerTraceHistoryService {

    @Autowired
    private CustomerTraceHistoryMapper customertracehistoryMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CustomerTraceHistory queryById(Long id) {
        return this.customertracehistoryMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<CustomerTraceHistory> queryAll() {
        return this.customertracehistoryMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<CustomerTraceHistory> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTraceHistory> list = customertracehistoryMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param customertracehistory 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerTraceHistory insert(CustomerTraceHistory customertracehistory) {
        customertracehistory.setInputTime(new Date());
        customertracehistory.setInputUser((Employee) SecurityUtils.getSubject().getPrincipal());
        this.customertracehistoryMapper.insert(customertracehistory);
        return customertracehistory;
    }

    /**
     * 修改数据
     *
     * @param customertracehistory 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerTraceHistory update(CustomerTraceHistory customertracehistory) {
        this.customertracehistoryMapper.update(customertracehistory);
        return this.queryById(customertracehistory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.customertracehistoryMapper.deleteById(id) > 0;
    }
}