package com.coderZsq;

/*
 * 静态导入
 * 使用了静态导入后，就可以省略类名来访问静态成员(成员变量、方法、嵌套类)
 * */

import static com.coderZsq.other.Test.*;
import static java.lang.Math.PI;

public class Main {

    /*
     * static
     *
     * static 常用来修饰类的成员:成员变量、方法、嵌套类
     *
     * 成员变量
     * 被 static 修饰:类变量，静态变量，静态字段
     * 在程序运行过程中只占用一份固定的内存(存储在方法区)
     * 可以通过实例、类访问
     *
     * 没有被 static 修饰:实例变量
     * 在每个实例内部都有一份内存
     * 只能通过实例访问，不可以通过类访问
     *
     * 不推荐使用实例访问类变量、类方法
     * 在同一个类中
     * 不能有同名的实例变量和类变量，不能有相同签名的实例方法和类方法
     *
     * 方法
     * 被 static 修饰:类方法、静态方法
     * 可以通过实例、类访问
     * 内部不可以使用 this
     * 可以直接访问类变量、类方法
     * 不可以直接访问实例变量、实例方法
     *
     * 没有被 static 修饰:实例方法
     * 只能通过实例访问，不可以通过类访问
     * 内部可以使用 this
     * 可以直接访问实例变量、实例方法
     * 可以直接访问类变量、类方法
     * */
    public static void main(String[] args) {
        {
            System.out.println(age);
            show(); // age is 1
            Other other = new Other();
            other.other(); // other
        }
        /*
         * 静态导入的经典使用场景
         *
         * 正确使用静态导入，可以消除一些重复的类名，提高代码可读性
         * 过度使用静态导入，会让读者分不清静态成员是在哪个类中定义的
         * 建议:谨慎使用静态导入
         * */
        {
            System.out.println(2 * PI * 10);
            System.out.println(2 * PI * 20);
        }
    }
}
