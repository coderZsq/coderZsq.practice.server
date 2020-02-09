package com.coderZsq._05_anno;

import java.lang.annotation.Annotation;

// 第三方程序, 来赋予注解功能
public class VIPDemo {
    public static void main(String[] args) {
        // 需求: 获取Employee上所有的注解
        // 1: 获取Employee字节码对象
        Class<Employee> clz = Employee.class;
        // 2: 直接获取类上所有的注解
//        Annotation[] as = clz.getAnnotations();
//        for (Annotation a : as) {
//            System.out.println(a);
//        }
        // 判断Employee类上是否使用VIP注解
        VIP a = clz.getAnnotation(VIP.class);
        if (a == null) {
            System.out.println("没有");
        } else {
            System.out.println("有");

            System.out.println(a.value());
            System.out.println(a.age());
            System.out.println(a.hobby());
        }
    }
}
