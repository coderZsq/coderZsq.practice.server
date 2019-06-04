package com.coderZsq._03_introspector;

import com.coderZsq._02_javabean.Person;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BeanUtils {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setId(123L);
        person.setName("Castie!");
        person.setAge(18);
        Map<String, Object> map = bean2map(person);
        System.out.println(map);
        Person object = map2bean(map, Person.class);
        System.out.println(object);
    }

    public static Map<String, Object> bean2map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
            String name = propertyDescriptor.getName();
            Object value = propertyDescriptor.getReadMethod().invoke(bean);
            map.put(name, value);
        }
        return map;
    }

    public static <T> T map2bean(Map<String, Object> map, Class<T> beanType) throws Exception {
        Object object = beanType.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {
            Object value = map.get(propertyDescriptor.getName());
            propertyDescriptor.getWriteMethod().invoke(object, value);
        }
        return (T)object;
    }
}
