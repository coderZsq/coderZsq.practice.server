package com.coderZsq._01_junit.asserted;

/**
 * 数学运算功能
 */
public interface IMath {
    /**
     * 两个数相加
     * @param a 加数
     * @param b 加数
     * @return 两个数之和
     */
    int add(int a, int b);

    /**
     * 两个数之商 (考虑整除)
     * @param a 被整除
     * @param b 除数
     * @return 商
     */
    int divide(int a, int b);
}
