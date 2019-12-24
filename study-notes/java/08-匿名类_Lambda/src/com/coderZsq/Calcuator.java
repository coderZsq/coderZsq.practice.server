package com.coderZsq;

/*
 * Lambda 实例
 *
 * 参数列表可以省略参数类型
 *
 * 当只有一条语句时
 * 可以省略大括号、分号、return
 *
 * 当只有一个参数时
 * 可以省略小括号
 *
 * 当没有参数时
 * 不能省略小括号
 * */
@FunctionalInterface
public interface Calcuator {
    int calculate(int v1, int v2);

    static void execute(int v1, int v2, Calcuator c) {
        System.out.println(c.calculate(v1, v2));
    }

    static void main(String[] args) {
        execute(10, 20, (int v1, int v2) -> {
            return v1 + v2;
        });  // 30

        execute(11, 22, (v1, v2) -> v1 + v2); //33
    }
}