package com.coderZsq;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    /**
     * 求 1 + 2 + 3 + ... + (n - 1) + n 的值
     * @param n
     * @return
     */
    int sum(int n) {
        if (n <= 1) return n;
        return n + sum(n - 1);
    }
}
