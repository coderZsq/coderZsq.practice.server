package com.sq;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ProjectB {
    public static void main(String[] args) {
        System.out.println(ProjectC.class);
        System.out.println(SpringBeanAutowiringSupport.class);
        System.out.println(ProjectD.class);
        System.out.println(SqlSessionFactory.class);
    }
}
