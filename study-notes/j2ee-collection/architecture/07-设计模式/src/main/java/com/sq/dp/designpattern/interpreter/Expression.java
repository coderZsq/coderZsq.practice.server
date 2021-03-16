package com.sq.dp.designpattern.interpreter;

/**
 * 抽象表达式: 定义了解释方法接口
 */
public interface Expression {
    boolean interpret(String info);
}
