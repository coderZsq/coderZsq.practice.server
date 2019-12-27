package com.coderZsq;

/*
 * 枚举的使用注意
 *
 * 枚举的本质是类，所有枚举类型最终都隐式继承自 java.lang.Enum
 *
 * 枚举定义完常量后，可以再定义成员变量、方法等内容(这时最后一个常量要以分号结束)
 *
 * 枚举的构造方法权限必须是 无修饰符 或者 private
 * Java 会主动调用构造方法初始化每一个常量，你不能主动调用构造方法
 * */
public enum Season {
    SPRING, SUMMER, FALL, WINTER
}
