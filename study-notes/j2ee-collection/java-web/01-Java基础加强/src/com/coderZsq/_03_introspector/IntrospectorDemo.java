package com.coderZsq._03_introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

// 内省机制: 操作User类值的属性
public class IntrospectorDemo {
    public static void main(String[] args) throws Exception {
        // 1.获取JavaBean的描述对象
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        User u = User.class.newInstance();
        // 2.获取JavaBean中的属性的描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        System.out.println(u);
        for (PropertyDescriptor pd : pds) {
            // 获取当前属性的名称
            System.out.println("属性名=" + pd.getName());
            // 获取当前属性的getter方法
            System.out.println("getter:" + pd.getReadMethod());
            // 获取当前属性的setter方法
            System.out.println("setter:" + pd.getWriteMethod());
            System.out.println("------------------------------------");

            if ("name".equals(pd.getName())) {
                Method setter = pd.getWriteMethod();
                setter.invoke(u, "coderZsq");
            }
        }
        System.out.println(u);
    }
}
