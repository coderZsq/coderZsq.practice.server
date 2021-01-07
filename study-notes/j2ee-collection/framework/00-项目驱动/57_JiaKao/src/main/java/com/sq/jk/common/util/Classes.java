package com.sq.jk.common.util;

import com.sq.jk.common.enhance.Stop;
import com.sq.jk.pojo.po.DictType;

import java.lang.reflect.Field;

public class Classes {
    /**
     * 返回第一个不是Object.class的类
     */
    public static Class<?> notObject(Class<?>... sources) {
        if (sources == null) return null;
        for (Class<?> source : sources) {
            if (!source.equals(Object.class)) return source;
        }
        return null;
    }

    /**
     * 获取cls类中的fieldName属性
     */
    public static Field getField(Class<?> cls, String fieldName) throws Exception {
        return enumerateFields(cls, (field, curCls) -> {
            if (field.getName().equals(fieldName)) return Stop.create(field);
            return null;
        });
    }

    /**
     * 遍历cls的所有属性
     */
    public static <T> T enumerateFields(Class<?> cls,
                                        FieldConsumer<T> fieldConsumer) throws Exception {
        if (fieldConsumer == null || cls == null) return null;
        Class<?> curCls = cls;
        while (!curCls.equals(Object.class)) {
            for (Field field : curCls.getDeclaredFields()) {
                Stop<T> stop = fieldConsumer.accept(field, curCls);
                if (stop != null) return stop.getData();
            }
            curCls = curCls.getSuperclass();
        }
        return null;
    }

    public interface FieldConsumer<T> {
        Stop<T> accept(Field field, Class<?> ownerCls) throws Exception;
    }

    public static void main(String[] args) throws Exception {
        Object result = enumerateFields(DictType.class, (field, cls) -> {
            System.out.println(field.getName());
            if (field.getName().equals("name")) return Stop.create("abc");
            return null;
        });
        System.out.println(result);
    }
}
