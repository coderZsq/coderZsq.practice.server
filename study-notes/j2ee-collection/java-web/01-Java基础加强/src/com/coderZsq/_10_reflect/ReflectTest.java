package com.coderZsq._10_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

// 演示反射操作
@SuppressWarnings("all")
public class ReflectTest {
    @Test
    public void testGetClass() throws Exception {
        // 1 通过Class的静态方法forName(全限定名)
        Class clz1 = Class.forName("com.coderZsq._10_reflect.Person");
        System.out.println(clz1);

        // 2 通过任何对象去调用getClass() 方法来获取字节码对象
        Person p = new Person();
        Class clz2 = p.getClass();
        System.out.println(clz2);

        // 3 任何类型都有一个class属性
        Class clz3 = Person.class;
        Class clz4 = int.class;

        System.out.println(clz1 == clz2);
        System.out.println(clz2 == clz3);
    }

    @Test
    public void testGetConstructor() throws Exception {
        Class clz = Person.class;
        // 1)获取所有public构造器
        Constructor[] cs1 = clz.getConstructors();
        for (Constructor c : cs1) {
            // System.out.println(c);
        }
        // 2)获取所有构造器包括private
        Constructor[] dcs1 = clz.getDeclaredConstructors();
        for (Constructor c : dcs1) {
            // System.out.println(c);
        }
        // 3)获取无参数public构造器
        // getConstructor(int.class) ---> new Class[](int.class)
        // getConstructor() ---> new Class[]{}
        Constructor c = clz.getConstructor();
        // System.out.println(c);
        // 4)获取带有指定参数的public构造器
        Constructor c2 = clz.getConstructor(Long.class, String.class, int.class);
        // System.out.println(c2);
        // 5)获取带有指定参数的private构造器
        Constructor c3 = clz.getDeclaredConstructor(String.class, int.class);
        System.out.println(c3);
    }

    @Test
    public void testCreateObject() throws Exception {
        // 1. 获取字节码对象
         Class clz = Person.class;
        // 2. 通过字节码对象去获取到构造器
        Constructor c = clz.getConstructor(String.class);
        // 3. 调用构造器来创建对象
        Object obj = c.newInstance("coderZsq");
        // System.out.println(obj);

        // 获取私有化的构造器
        Constructor c2 = clz.getDeclaredConstructor(String.class, int.class);
        c2.setAccessible(true);
        Object obj2 = c2.newInstance("coderZsq", 18);
        System.out.println(obj2);

        // 通过公共的无参的构造器去创建对象, 快捷方式
        // Class 类中有一个的方法newInstance();
        Object obj4 = clz.newInstance();
        System.out.println(obj4);
    }
}
