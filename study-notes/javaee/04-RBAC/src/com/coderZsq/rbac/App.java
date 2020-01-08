package com.coderZsq.rbac;


import com.coderZsq.rbac.domain.Department;
import com.coderZsq.rbac.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class App {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testSelectAll() throws Exception {
        List<Department> departments = departmentService.selectAll();
        System.out.println(departments);
    }

    @Test
    public void testInsert() throws Exception {
        departmentService.insert(new Department(10L, "testinsert", "008"));

    }
}
