package com.coderzsq._04_mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class DataSourceController {
    @Autowired
    private DataSource dataSource;

    @RequestMapping("ds")
    public String ds() throws Exception {
        System.out.println(6666);
        // 创建连接
        System.out.println(dataSource.getConnection());
        // 查看数据库连接池状态
        System.out.println("dataSource = " + dataSource);
        return "success";
    }
}
