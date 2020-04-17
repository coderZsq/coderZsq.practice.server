package com.coderzsq._09_condition;

import com.coderzsq._03_bean.OtherBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping("bean")
    public String testBean() {
        System.out.println("context.getBeansOfType(OtherBean.class).size() = " + context.getBeansOfType(OtherBean.class).size());
        return "success";
    }
}
