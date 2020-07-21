package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.CustomerTransfer;
import com.coderZsq.crm.domain.Employee;
import com.coderZsq.crm.query.CustomerTransferQueryObject;
import com.coderZsq.crm.service.CustomerTransferService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Customertransfer)表控制层
 *
 * @author makejava
 * @since 2020-04-01 10:17:34
 */
@Controller
@RequestMapping("customerTransfer")
public class CustomerTransferController {
    /**
     * 服务对象
     */
    @Autowired
    private CustomerTransferService customertransferService;


    /**
     * 台账数据
     * @param model
     * @param qo
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model, CustomerTransferQueryObject qo) {
        model.addAttribute("pageInfo", customertransferService.query(qo));
        model.addAttribute("qo", qo);
        return "customertransfer/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(CustomerTransfer customertransfer) {
        if (customertransfer != null && customertransfer.getId() != null) {
            customertransferService.update(customertransfer);
        } else {
            customertransferService.insert(customertransfer);
        }
        return PageResult.success();
    }

    /**
     * 吸纳
     */
    @RequestMapping("/absorb")
    @ResponseBody
    public PageResult absorb(CustomerTransfer customertransfer) {
        customertransferService.absorb(customertransfer);
        return PageResult.success();
    }
}