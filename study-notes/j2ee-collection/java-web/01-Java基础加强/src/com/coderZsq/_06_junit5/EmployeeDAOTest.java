package com.coderZsq._06_junit5;

public class EmployeeDAOTest {
    @MyBefore
    public void init() throws Exception {
        System.out.println("初始化");
    }
    @MyAfter
    public void destroy() throws Exception {
        System.out.println("销毁");
    }
    @MyTest
    public void testSave() throws Exception {
        System.out.println("测试保存");
    }
    @MyTest
    public void testDelete() throws Exception {
        System.out.println("测试删除");
    }
}
