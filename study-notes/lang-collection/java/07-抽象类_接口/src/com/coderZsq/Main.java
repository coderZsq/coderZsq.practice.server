package com.coderZsq;

public class Main {
    /*
     * 接口(Interface)
     *
     * 看到“接口”二字首先想到的什么?
     * 网线接口?USB 接口?
     *
     * 接口的英文单词是 Interface， 这个单词是否很熟悉?
     *
     * API(Application Programming Interface)
     * 应用编程接口，提供给开发者调用的一组功能(无须提供源码)
     *
     * Java 中的接口
     * 一系列方法声明的集合
     * 用来定义规范、标准
     * */
    public static void main(String[] args) {
        /*
         * 接口中可以定义的内容
         *
         * 可以定义:抽象方法、常量、嵌套类型，从 Java 8 开始可以定义:默认方法、静态方法(类方法)
         * 上述可以定义的内容都是隐式 public 的，因此可以省略 public 关键字
         * 从 Java 9 开始可以定义 private 方法
         *
         * 常量可以省略 static、final
         *
         * 抽象方法可以省略 abstract
         *
         * 不能自定义构造方法、不能定义(静态)初始化块、不能实例化
         * */

        /*
         * 接口的细节
         *
         * 接口名称可以在任何使用类型的地方使用
         *
         * 一个类可以通过 implements 关键字实现一个或多个接口
         * 实现接口的类必须实现接口中定义的所有抽象方法，除非它是个抽象类
         * 如果一个类实现的多个接口中有相同的抽象方法，只需要实现此方法一次 extends 和 implements 可以一起使用，implements 必须写在 extends 的后面
         * 当父类、接口中的方法签名一样时，那么返回值类型也必须一样
         *
         * 一个接口可以通过 extends 关键字继承一个或者多个接口
         * 当多个父接口中的方法签名一样时，那么返回值类型也必须一样
         * */

        /*
         * 接口的升级问题
         *
         * 如果接口需要升级，比如增加新的抽象方法
         * 会导致大幅的代码改动，以前实现接口的类都得改动
         *
         * 若想在不改动以前实现类的前提下进行接口升级，从 Java 8 开始，有 2 种方案
         * 默认方法(Default Method)
         * 静态方法(Static Method)
         * */

        /*
         * 默认方法的使用
         *
         * 当一个类实现的接口中有默认方法时，这个类可以
         * 啥也不干，沿用接口的默认实现
         * 重新定义默认方法，覆盖默认方法的实现
         * 重新声明默认方法，将默认方法声明为抽象方法(此类必须是抽象类)
         *
         * 当一个接口继承的父接口中有默认方法时，这个接口可以
         * 啥也不干，沿用接口的默认实现
         * 重新定义默认方法，覆盖默认方法的实现
         * 重新声明默认方法，将默认方法声明为抽象方法
         * */
        {
            Dog dog = new Dog();
            dog.eat("bone");
            // Eatable - eat - bone

            Cat cat = new Cat();
            cat.eat("fish");
            // Eatable - eat - fish
            // Cat - eat - fish
        }

        /*
         * 抽象类与接口对比
         *
         * 抽象类和接口的用途还是有点类似，该如何选择?
         *
         * 何时选择抽象类?
         * 在紧密相关的类之间共享代码
         * 需要除 public 之外的访问权限
         * 需要定义实例变量、非 final 的静态变量
         *
         * 何时选择接口?
         * 不相关的类实现相同的方法
         * 只是定义行为，不关心具体是谁实现了行为
         * 想实现类型的多重继承
         * */

        /*
         * 多态(Polymorphism)
         *
         * 什么是多态?
         * 具有多种形态
         * 同一操作作用于不同的对象，产生不同的执行结果
         *
         * 多态的体现
         * 父类(接口)类型指向子类对象
         * 调用子类重写的方法
         *
         * JVM 会根据引用变量指向的具体对象来调用对应的方法
         * 这个行为叫做:虚方法调用(virtual method invocation)
         * 类似于 C++ 中的虚函数调用
         * */
        {
            speak(new Dog6());  // Dog - wangwang
            speak(new Cat2());  // Cat - miaomiao
        }

        {
            run(new Pig());  // Pig - run
            run(new Person());  // Person - run
        }

        {
            Dog7.run();  // Dog - run
            Animal5.run();  // Animal - run

            Dog7 dog1 = new Dog7();
            dog1.run(); // Dog - run

            Animal5 dog2 = new Dog7();
            dog2.run(); // Animal - run
        }

        {
            Student stu1 = new Student();
            System.out.println(stu1.age); // 2
            System.out.println(stu1.getPAge()); // 1
            System.out.println(stu1.getSAge()); // 2

            Person stu2 = new Student();
            System.out.println(stu2.age); // 1
            System.out.println(stu2.getPAge()); // 1

            Teacher tea1 = new Teacher();
            System.out.println(tea1.age);  // 3
            System.out.println(tea1.getPAge()); // 3
            System.out.println(tea1.getTAge()); // 3

            Person tea2 = new Teacher();
            System.out.println(tea2.age); // 1
            System.out.println(tea2.getPAge()); // 3
        }

        /*
         * instanceof
         *
         * 可以通过instanceof 判断某个类型是否属于某种类型
         * */
        {
            Object dog = new Dog2();
            System.out.println(dog instanceof Dog2); // true
            System.out.println(dog instanceof Animal); // true
            System.out.println(dog instanceof Runnable); // true
            System.out.println(dog instanceof String);  // false
        }

        /*
         * 对象数组的注意点
         * */
        {
            Object obj1 = 11;
            Integer obj2 = (Integer) obj1;
            System.out.println(obj2);

//            Object[] objs1 = new Object[] {11, 22, 33};
            Object[] objs1 = {11, 22, 33};
//            java.lang.ClassCastException
//            [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer
            Integer[] objs2 = (Integer[]) objs1;
            System.out.println(objs2);
        }
    }

    static void speak(Animal4 animal) {
        animal.speak();
    }

    static void run(Runnable2 runnable) {
        runnable.run();
    }

    static void speak(Animal animal) {
        if (animal instanceof Dog8) {
            ((Dog8) animal).wang();
        } else if (animal instanceof Cat3) {
            ((Cat3) animal).miao();
        }
    }
}
