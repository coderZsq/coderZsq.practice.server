package com.coderZsq;

/*
 * 如何使用一个类
 *
 * 要想正常使用一个类，必须得知道这个类的具体位置(在哪个包)，有 3 种常见方式来使用一个类
 * */
// 2 使用 import 导入指定的类名

import com.coderZsq.model.Dog;
// 3 使用 import 导入整个包的所有类
import com.coderZsq.model.*;

/*
 * 导入的细节
 *
 * 为了方便，Java 编译器会为每个源文件自动导入 2 个包
 * import java.lang.*;
 * java.lang 包提供了 Java 开发中最常用的一些类型
 * import 源文件所在包.*;
 *
 * import aa.bb.*;
 * 仅仅是 import 了直接存放在 aa.bb 包中的类型
 * 并不包含 import aa.bb.xx.*;
 *
 * Eclipse 中导包的快捷键:Ctrl + Shift + O，也可以使用 Ctrl + 1 修复错误来导包
 * */
import com.coderZsq.model.Person;

public class Main {

    public static void main(String[] args) {
        /*
         * 对象的内存
         *
         * Java 中所有对象都是 new 出来的，所有对象的内存都是在堆空间，所有保存对象的变量都是引用类型
         *
         * Java 运行时环境有个垃圾回收器(garbage collector，简称GC)，会自动回收不再使用的内存
         * 当一个对象没有任何引用指向时，会被GC回收掉内存
         * */
        {
            Dog dog = new Dog();
            dog.age = 20;
            dog.weight = 5.6;
            dog.run();
            dog.eat("apple");
        }

        /*
         * 复杂对象的内存
         * */
        {
            Dog dog = new Dog();
            dog.price = 100;

            Person person = new Person();
            person.age = 20;
            person.dog = dog;
        }

        /*
         * 对象数组的内存
         * */
        {
            Dog[] dogs = new Dog[7];
            for (int i = 0; i < dogs.length; i++) {
                dogs[i] = new Dog();
            }
            dogs[6] = null;
        }

        /*
         * 思考: 方法存储在哪里?
         * */
        {
            Dog dog1 = new Dog();
            dog1.price = 100;
            dog1.run();
            dog1.eat();

            Dog dog2 = new Dog();
            dog2.price = 200;
            dog2.run();
            dog2.eat();
        }

        /*
         * Java程序的内存划分
         *
         * Java 虚拟机在执行 Java 程序时会将内存划分为若干个不同的数据区域，主要有
         * PC 寄存器(Program Counter Register):存储 Java 虚拟机正在执行的字节码指令的地址
         * Java 虚拟机栈(Java Virtual Machine Stack):存储栈帧
         *
         * 堆(Heap):存储 GC 所管理的各种对象
         *
         * 方法区(Method Area):存储每一个类的结构信息(比如字段和方法信息、构造方法和普通方法的字节码等)
         *
         * 本地方法栈(Native Method Stack):用来支持 native 方法的调用(比如用 C 语言编写的方法)
         * */

        /*
         * 如何使用一个类
         *
         * 1 使用类的全名
         * */
        {
            com.coderZsq.model.Dog dog = new com.coderZsq.model.Dog();
        }
    }
}
