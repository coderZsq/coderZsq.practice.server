package com.coderzsq._02_configproperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSourceController {
    @Autowired
    private MyDruidDataSource ds;

    @RequestMapping("ds")
    public String ds() {
        System.out.println("ds = " + ds);
        return "success";
    }
}
