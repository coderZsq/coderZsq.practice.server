package com.hesj.demo;

import com.sun.corba.se.impl.orb.ParserTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Testspring {

    @Autowired
    private IDepartmentService departmentService;


    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testAnno() throws Exception {
        departmentService.get(1L);
    }

    @Test
    public void testContext() throws Exception {
        IDepartmentService bean = applicationContext.getBean(IDepartmentService.class);
        bean.get(1L);
    }
}
