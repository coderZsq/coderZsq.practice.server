package com.coderZsq;

/*
 * 自定义异常
 *
 * 开发中自定义的异常类型，基本都是以下 2 种做法
 *
 * 继承自 Exception
 * 使用起来代码会稍微复杂
 * 希望开发者重视这个异常、认真处理这个异常
 *
 * 继承自 RuntimeException
 * 使用起来代码会更加简洁
 * 不严格要求开发者去处理这个异常
 * */
public class EmptyNameException extends RuntimeException {
    public EmptyNameException() {
        super("name must not be empty.");
    }
}
