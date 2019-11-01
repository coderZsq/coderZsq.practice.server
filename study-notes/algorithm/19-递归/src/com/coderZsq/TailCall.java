package com.coderZsq;

public class TailCall {
    public static void main(String[] args) {
        System.out.println(facttorial1(3));
    }

    static int fib0(int n) {
        if (n <= 2) return 1;
        return fib0(n - 1) + fib0(n - 1);
    }

    static int fib1(int n) {
        return fib1(n, 1, 1);
    }

    static int fib1(int n, int first, int second) {
        if (n <= 1) return first;
        return fib1(n - 1, second, first + second);
    }

    /**
     * 1 * 2 * 3 * 4 * ... * (n - 1) * n
     * @param n
     * @return
     */
    static int facttorial1(int n) {
        return facttorial1(n, 1);
    }

    static int facttorial1(int n, int result) {
        if (n <= 1) return result;
        return facttorial1(n - 1, n * result);
    }

    static int facttorial0(int n) {
        if (n <= 1) return n;
        return n * facttorial0(n - 1);
    }

    void test0(int n) {
        if (n < 0) return;
        System.out.println("test - " + n);
        test0(n - 1);
    }

    void test1(int n) {
        if (n < 0) return;
        while (n >= 0) {
            System.out.println("test - " + n);
            n--;
        }
    }

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
