package com.foundation;

/**
 * Created by zhushuangquan on 16/01/2018.
 */
public class Induction {

    public static void run() {
        System.out.println("--- Induction ---");
        System.out.println(sum(10));
        System.out.println(sum(100));
        System.out.println(sum(1000));
        System.out.println(sum(10000));
    }
    /*
    * 严格定义递归函数作用, 包括参数, 返回值, Side-effect
    * 先一般, 后特殊
    * 每次调用必须缩小问题规模
    * 每次问题规模缩小程度必须为1
    * */
    private static int sum(int n) {
        if (n == 1) return 1;
        return sum(n - 1) + n;
    }

}
