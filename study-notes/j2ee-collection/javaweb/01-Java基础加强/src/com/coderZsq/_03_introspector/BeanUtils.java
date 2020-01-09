package com.coderZsq._03_introspector;

import com.coderZsq._02_javabean.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

// JavaBean的工具类
public class BeanUtils {

    public static void main(String[] args) throws Exception {
        Person p = new Person();
        p.setId(123L);
        p.setName("张三");
        p.setAge(18);

        Map<String, Object> map = bean2map(p);
        System.out.println(map);
        Person obj = map2Bean(map, Person.class); // Class<Person> cls = Person.class;
        System.out.println(obj);
    }

    // 把JavaBean对象转换为Map
    public static Map<String, Object> bean2map(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName(); // 属性名称
            Object value = pd.getReadMethod().invoke(bean); // 调用属性的getter方法, 获取属性值
            map.put(name, value);
        }
        return map;
    }

    // 把Map转换为JavaBean
    public static <T> T map2Bean(Map<String, Object> map, Class<T> beanType) throws Exception {
        // 创建JavaBean对象
        Object obj = beanType.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            // 从Map中获取和属性同名的key的值
            Object value = map.get(pd.getName());
            // 调用setter方法, 设置属性值
            pd.getWriteMethod().invoke(obj, value);
        }
        return (T) obj;
    }
}
