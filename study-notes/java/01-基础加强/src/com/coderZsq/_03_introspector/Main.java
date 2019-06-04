package com.coderZsq._03_introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        User user = User.class.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        System.out.println(user);
        for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
            System.out.println("属性名: " + propertyDescriptor.getName());
            System.out.println("getter: " + propertyDescriptor.getReadMethod());
            System.out.println("setter: " + propertyDescriptor.getWriteMethod());
            System.out.println("-----------------------------------------");

            if ("name".equals(propertyDescriptor.getName())) {
                Method setter = propertyDescriptor.getWriteMethod();
                setter.invoke(user, "Casite!");
            }
        }
        System.out.println(user);
    }
}
