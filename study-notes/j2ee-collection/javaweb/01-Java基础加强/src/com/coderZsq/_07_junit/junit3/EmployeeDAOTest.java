package com.coderZsq._01_junit.junit3;


import junit.framework.TestCase;

// EmployeeDAO组件的测试类
public class EmployeeDAOTest extends TestCase {

    // 初始化操作
    @Override
    public void setUp() throws Exception {
        System.out.println("初始化操作");
    }

    // 销毁操作
    @Override
    protected void tearDown() throws Exception {
        System.out.println("销毁操作");
    }

    // 测试员工的保存操作
    public void testSave() throws Exception {
        System.out.println("员工保存");
    }

    // 测试员工的删除操作
    public void testDelete() throws Exception {
        System.out.println("员工删除");
    }
}
