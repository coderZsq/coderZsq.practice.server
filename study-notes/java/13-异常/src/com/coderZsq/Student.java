package com.coderZsq;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * throws 的细节
 *
 * 当父类的方法没有throws 异常
 * 子类的重写方法也不能 throws 异常
 *
 * 当父类的方法有throws 异常
 * 子类的重写方法可以
 * 不throws 异常
 * throws 跟父类一样的异常
 * throws 父类异常的子类型
 * */
public class Student extends Person {
    @Override
    public void test1() {
    }

    @Override
    public void test2() {
    }

    @Override
    public void test3() throws IOException {
    }

    @Override
    public void test4() throws FileNotFoundException {
    }
}
