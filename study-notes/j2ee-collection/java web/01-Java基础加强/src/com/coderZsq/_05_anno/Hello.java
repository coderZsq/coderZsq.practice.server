package com.coderZsq._05_anno;

import java.util.Date;
import java.util.List;
import java.util.Set;

// 认识一下: 我们曾经用过JDK中的注解
@SuppressWarnings("all")
public class Hello {
    /**
     * @deprecated
     */
    public void doWork11() {
        Set set = null;
        List list = null;
    }

    @Deprecated
    public void doWork1() {
        @SuppressWarnings({"rawtypes", "unused"})
        Set set = null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @SuppressWarnings("deprecation")
    public void doWork2() {
        Date d = new Date();
        d.toLocaleString();
    }
}
