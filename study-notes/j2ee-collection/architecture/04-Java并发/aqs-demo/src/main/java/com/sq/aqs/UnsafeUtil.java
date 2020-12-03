package com.sq.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {
    public static Unsafe getInstance() {
        // 通过反射的方式获取对象
        Field unsafe = null;
        // 获取到Unsafe类里面的unsafe字段
        try {
            // 获取到字段
            unsafe = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置访问权限
            unsafe.setAccessible(true);
            return (Unsafe) unsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
