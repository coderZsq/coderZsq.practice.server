package com.sq.impl.v1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {
    public static Unsafe getInstance() {
        // private static final Unsafe theUnsafe
        Field field = null;
        try {
            // 1 获取到对应的属性
            field = Unsafe.class.getDeclaredField("theUnsafe");
            // 2 设置属性可见
            field.setAccessible(true);
            // 3 获取对应的值
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
