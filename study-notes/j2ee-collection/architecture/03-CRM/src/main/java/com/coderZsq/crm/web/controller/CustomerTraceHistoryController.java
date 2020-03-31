package com.coderZsq.crm.web.controller;

import com.coderZsq.crm.common.PageResult;
import com.coderZsq.crm.domain.CustomerTraceHistory;
import com.coderZsq.crm.query.CustomerTraceHistoryQueryObject;
import com.coderZsq.crm.service.CustomerTraceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (CustomerTraceHistory)表控制层
 *
 * @author makejava
 * @since 2020-03-29 20:54:49
 */
@Controller
@RequestMapping("customerTraceHistory")
public class CustomerTraceHistoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CustomerTraceHistoryService customertracehistoryService;

    @RequestMapping("/list")
    public String list(Model model, CustomerTraceHistoryQueryObject qo){
        model.addAttribute("pageInfo",customertracehistoryService.query(qo));
        model.addAttribute("qo",qo);
        return "customerTraceHistory/list";
    }


    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public PageResult saveOrUpdate(CustomerTraceHistory customertracehistory){
        if(customertracehistory!=null&& customertracehistory.getId()!=null){
            customertracehistoryService.update(customertracehistory);
        }else{
            customertracehistoryService.insert(customertracehistory);
        }
        return PageResult.success();
    }
}