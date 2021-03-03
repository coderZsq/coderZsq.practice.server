package com.sq.dp.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * 里氏替换原则
 * 主要针对继承方面来进行约束的
 * 1. 子类可以实现父类的抽象方法, 但不能覆盖父类的非抽象方法
 * 2. 子类可以增加自己特有的方法
 * 3. 当子类的方法重载父类的方法时, 方法的形参要比父类方法的输入参数更宽松
 * 4. 当子类的方法实现父类的抽象方法时, 方法的返回值应比父类更严格
 * 继承:
 * 优点:
 * 1. 提升代码复用性
 * 2. 代码结构"更加清晰" --> 继承地狱
 * 缺点:
 * 1. 继承如果过于庞大, 会导致整个代码变得不清晰
 * 2. 代码耦合性变高
 * 3. 代码稳定性变差, 并且由于子类可以重写父类方法, 当重写以后会导致运行时起不确定具体调用的是父类实现还是子类实现而出现bug
 * 4. 如果有多处直接使用到父类方法的实现, 但凡当父类方法修改以后, 所有依赖的地方都得考虑兼容性
 * <p>
 * 如果基类是一个抽象类, 而这个方法已经实现了, 子类尽量不要覆写. 类间依赖的是抽象, 覆写了抽象方法, 对依赖的稳定性会有一定的影响
 */
public class Liskov {
    public static void main(String[] args) {
        // 调用父类的 num1 - num2
        A a = new A();
        a.func1(5, 3);
        // 调用子类的 num1 - num2
        B b = new B();
        b.func1(9, 0);
        // 调用子类的 num1 * num2
        b.func2(1, 9);

        // 测试子类重载父类方法, 方法形参比父类更宽松
        b.test(new ArrayList());
        List list = new ArrayList();
        b.test(list);
    }
}

class A {
    // num1 - num2
    public int func1(int num1, int num2) {
        System.out.println("-----父类: func1---> num1 - num2");
        return num1 - num2;
    }

    public void test(ArrayList list) {
        System.out.println("这是父类的测试方法");
    }
}

class B extends A {
    public void test(List list) {
        System.out.println("这是子类的测试方法");
    }

    @Override
    public int func1(int num1, int num2) {
        System.out.println("-----子类: func1---> num1 + num2");
        return num1 + num2;
    }

    public int func2(int num1, int num2) {
        System.out.println("-----子类: func1---> num1 * num2");
        return num1 * num2;
    }
}