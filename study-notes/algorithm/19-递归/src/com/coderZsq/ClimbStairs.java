package com.coderZsq;

public class ClimbStairs {

    int climbStair0(int n) {
        if (n <= 2) return n;
        return climbStair0(n - 1) + climbStair0(n - 2);
    }

    int climbStair1(int n) {
        if (n <= 2) return n;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }
}
