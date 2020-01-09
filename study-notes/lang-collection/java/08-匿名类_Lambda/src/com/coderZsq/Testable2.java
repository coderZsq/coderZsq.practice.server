package com.coderZsq;

/*
 * 引用类
 * */
@FunctionalInterface
public interface Testable2 {
    int test(int v1, int v2);

    static void main(String[] args) {
        Testable2 t1 = (v1, v2) -> Math.max(v1, v2);
        // 20
        System.out.println(t1.test(10, 20));

        Testable2 t2 = Math::max;
        // 20
        System.out.println(t2.test(10, 20));
    }
}
