package com.coderZsq._03_introspector;

import com.coderZsq._02_javabean.Person;

import java.util.Map;

// JavaBean的工具类
public class BeanUtils {

    public static void main(String[] args) {
        Person p = new Person();
        p.setId(123L);
        p.setName("张三");
        p.setAge(18);

        Map<String, Object> map = bean2map(p);
        System.out.println(map);
    }

    // 把JavaBean对象转换为Map
    public static Map<String, Object> bean2map(Object bean) {
        return null;
    }
}
