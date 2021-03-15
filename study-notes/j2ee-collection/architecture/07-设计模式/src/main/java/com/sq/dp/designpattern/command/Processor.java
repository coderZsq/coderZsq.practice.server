package com.sq.dp.designpattern.command;

/**
 * 接受者: 执行运算操作
 */
public class Processor {
    public void add(int num1, int num2) {
        System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
    }

    public void subtract(int num1, int num2) {
        System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
    }
}
