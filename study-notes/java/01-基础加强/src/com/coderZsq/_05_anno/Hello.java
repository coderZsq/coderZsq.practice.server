package com.coderZsq._05_anno;

import java.util.Date;
import java.util.Set;


public class Hello {

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
        Date date = new Date();
        date.toLocaleString();
    }
}
