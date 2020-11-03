package com.sq;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runners.JUnit4;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ProjectA {
    public static void main(String[] args) {
        System.out.println(ProjectB.class);
        System.out.println(ProjectC.class);
        System.out.println(SpringBeanAutowiringSupport.class);
        System.out.println(JUnit4.class);
        System.out.println(ProjectD.class);
        System.out.println(SqlSessionFactory.class);
    }
}
