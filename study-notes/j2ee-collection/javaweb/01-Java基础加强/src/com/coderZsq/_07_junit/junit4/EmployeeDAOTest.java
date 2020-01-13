package com.coderZsq._01_junit.junit4;

import org.junit.*;

// EmployeeDAO组件的测试类
public class EmployeeDAOTest {

    @BeforeClass
    public static void staticInit() throws Exception {
        System.out.println("staticInit");
    }

    @AfterClass
    public static void staticDestory() throws Exception {
        System.out.println("staticDestory");
    }

    @Before
    public void init() throws Exception {
        System.out.println("初始化操作");
    }

    @After
    public void destory() throws Exception {
        System.out.println("销毁操作");
    }

    @Test
    public void testSave() throws Exception {
        System.out.println("员工保存");
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("员工删除");
    }
}
