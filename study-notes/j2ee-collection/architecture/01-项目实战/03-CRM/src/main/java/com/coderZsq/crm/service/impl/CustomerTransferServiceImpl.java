package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Customer;
import com.coderZsq.crm.domain.CustomerTransfer;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.mapper.CustomerMapper;
import com.coderZsq.crm.mapper.CustomerTransferMapper;
import com.coderZsq.crm.service.CustomerTransferService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * (Customertransfer)表服务实现类
 *
 * @author makejava
 * @since 2020-04-01 10:17:34
 */
@Service("customertransferService")
public class CustomerTransferServiceImpl implements CustomerTransferService {

    @Autowired
    private CustomerTransferMapper customertransferMapper;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CustomerTransfer queryById(Long id) {
        return this.customertransferMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<CustomerTransfer> queryAll() {
        return this.customertransferMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<CustomerTransfer> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<CustomerTransfer> list = customertransferMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param customertransfer 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerTransfer insert(CustomerTransfer customertransfer) {
        customertransfer.setOperateTime(new Date());
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        customertransfer.setOperator(employee);
        this.customertransferMapper.insert(customertransfer);
        // 对客户的销售人员进行调整
        Customer customer = customerMapper.queryById(customertransfer.getCustomer().getId());
        customer.setSeller(customertransfer.getNewSeller());
        customerMapper.update(customer);
        return customertransfer;
    }

    /**
     * 修改数据
     *
     * @param customertransfer 实例对象
     * @return 实例对象
     */
    @Override
    public CustomerTransfer update(CustomerTransfer customertransfer) {
        this.customertransferMapper.update(customertransfer);
        return this.queryById(customertransfer.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.customertransferMapper.deleteById(id) > 0;
    }

    @Override
    public void absorb(CustomerTransfer customertransfer) {
        // 把单据的状态修改为潜在客户
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        customertransfer.setNewSeller(employee);
        customertransferMapper.insert(customertransfer);
        // 修改客户的状态
        Customer customer = customerMapper.queryById(customertransfer.getCustomer().getId());
        customer.setStatus(Customer.STATUS_POTENTIAL);
        customer.setSeller(employee);
        customerMapper.update(customer);
    }
}