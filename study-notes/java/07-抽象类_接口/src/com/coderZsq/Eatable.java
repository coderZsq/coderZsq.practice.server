package com.coderZsq;

public interface Eatable {
    /*
     * 默认方法(Default Method)
     *
     * 用 default 修饰默认方法
     * 默认方法只能是实例方法
     * */
    default void eat(String name) {
        System.out.println("Eatable - eat - " + name);
    }
}
