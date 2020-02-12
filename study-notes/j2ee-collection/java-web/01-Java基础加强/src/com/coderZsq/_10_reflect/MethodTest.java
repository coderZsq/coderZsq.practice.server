package com.coderZsq._10_reflect;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethodTest {
    @Test
    public void testGetMethod() throws Exception {
        // 获取字节码对象
        Class clz = Person.class;
        // 1) 获取所有public方法, 包括继承的
        Method[] ms = clz.getMethods();
        for (Method m : ms) {
            // System.out.println(m);
        }
        // 2) 获取所有方法, 包括private, 不包括继承的
        Method[] ms2 = clz.getDeclaredMethods();
        for (Method m : ms2) {
            // System.out.println(m);
        }
        // 3) 获取指定方法, 包括继承的
        // hashCode()
        Method ms3 = clz.getMethod("hashCode");
        // System.out.println(ms3);
        // 4) 获取指定方法, 包括private, 不包括继承的
        Method ms4 = clz.getDeclaredMethod("method5");
        System.out.println(ms4);
    }

    @Test
    public void testInvokeMethod() throws Exception {
        // 1 获取字节码对象
        Class clz = Person.class;
        Object obj = clz.newInstance();
        // 2 获取要调用的方法对象
        Method m1 = clz.getMethod("method4", String.class);
        // 3 执行方法
        Object ret = m1.invoke(obj, "Castie!");
        // System.out.println(ret);

        // 获取私有方法
        Method m2 = clz.getDeclaredMethod("method5");
        // 标记私有的方法可以被调用
        m2.setAccessible(true);
        m2.invoke(obj);

        // 使用反射调用静态方法
        Method m3 = clz.getMethod("method6", String.class);
        m3.invoke(null, "coderZsq");
    }
}
