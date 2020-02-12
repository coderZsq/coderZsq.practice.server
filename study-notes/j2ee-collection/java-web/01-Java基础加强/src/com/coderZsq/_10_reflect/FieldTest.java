package com.coderZsq._10_reflect;

import org.junit.Test;

import java.lang.reflect.Field;

// 演示字段
public class FieldTest {
    @Test
    public void testField() throws Exception {
        // 1 获取字节码对象
        Class clz = Person.class;
        Object obj = clz.newInstance();
        // 2 获取字段
        Field nameField = clz.getField("name");
        // 3 操作字段
        nameField.set(obj, "coderZsq");
        Object ret = nameField.get(obj);
        System.out.println(ret);
        // 私有字段
        Field idField = clz.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(obj, 11L);
        System.out.println(idField.get(obj));
    }
}
