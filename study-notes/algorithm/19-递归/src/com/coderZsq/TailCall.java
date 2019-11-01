package com.coderZsq;

public class TailCall {
    void test1() { // 20个字节
        int a = 10;
        int b = a + 10;
        test2(b);
    }

    void test2(int b) { // 10个字节
        int x1 = 20;
        int x2 = 30;
    }
}
