package com.coderZsq;

/*
 * Lambda Expression
 *
 * Lambda 表达式是 Java 8 开始才有的语法，发音:[ˈlæmdə]
 *
 * 函数式接口(Functional Interface):只包含 1 个抽象方法的接口
 * 可以在接口上面加上   注解，表示它是一个函数式接口
 *
 * 当匿名类实现的是函数式接口时，可以使用 Lambda 表达式进行简化
 * */
@FunctionalInterface
public interface Testable {
    void test(int v);
}
