package com.coderZsq._06_junit5;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作思路
 * 1): 获取EmployeeDAOTest这份字节码对象
 * 2): 获取EmployeeDAOTest中所有的方法
 * 3): 迭代出每一个方法, 并判断每一个方法分别使用了什么注解, 并归类存储
 *     beforeList: 存储@MyBefore标注的方法对象
 *     testList: 存储@MyTest标注的方法对象
 *     afterList: 存储@MyAfter标注的方法随心
 * 4): 循环迭代出testList中的每一个测试方法, 并执行
 *     执行beforeList中所有方法
 *     每一个测试方法运行
 *     执行afterList中所有方法
 * 5): 运行看效果
 */
public class JUnit5Mock {
    public static void main(String[] args) throws Exception {
        // 1): 获取EmployeeDAOTest这份字节码对象
        Class clz = EmployeeDAOTest.class;
        Object obj = clz.newInstance(); // 测试类对象
        // 2): 获取EmployeeDAOTest中所有的方法
        Method[] ms = clz.getMethods();
        // 3): 迭代出每一个方法, 并判断每一个方法分别使用了什么注解, 并归类存储
        List<Method> beforeList = new ArrayList<>();
        List<Method> testList = new ArrayList<>();
        List<Method> afterList = new ArrayList<>();
        for (Method method : ms) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeList.add(method);
            } else if (method.isAnnotationPresent(MyTest.class)) {
                testList.add(method);
            } else if (method.isAnnotationPresent(MyAfter.class)) {
                afterList.add(method);
            }
        }
        // 4): 循环迭代出testList中的每一个测试方法, 并执行
        for (Method m : testList) {
            for (Method bm : beforeList) {
                bm.invoke(obj);
            }
            m.invoke(obj); // 运行测试方法
            for (Method am : afterList) {
                am.invoke(obj);
            }
        }
    }
}
