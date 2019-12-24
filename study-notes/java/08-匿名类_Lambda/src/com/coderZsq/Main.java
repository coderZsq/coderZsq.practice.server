package com.coderZsq;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        /*
         * 匿名类(Anonymous Class)
         *
         * 当接口、抽象类的实现类，在整个项目中只用过一次，可以考虑使用匿名类
         * */
        {
            Person person = new Person();
            person.eat(new Eatable() {
                @Override
                public String name() {
                    return "Apple";
                }

                @Override
                public int energy() {
                    return 100;
                }
            }); // eat - Apple - 100

            Eatable beef = new Eatable() {
                @Override
                public String name() {
                    return "Beef";
                }

                @Override
                public int energy() {
                    return 500;
                }
            };
            person.eat(beef); // eat - Beef - 500
            person.eat(beef); // eat - Beef - 500
        }
        /*
         * 匿名类的使用注意
         *
         * 匿名类不能定义除编译时常量以外的任何 static 成员
         *
         * 匿名类只能访问 final 或者 有效 final 的局部变量
         *
         * 匿名类可以直接访问外部类中的所有成员(即使被声明为 private)
         * 匿名类只有在实例相关的代码块中使用，才能直接访问外部类中的实例成员(实例变量、实例方法)
         *
         * 匿名类不能自定义构造方法，但可以有初始化块
         *
         * 匿名类的常见用途
         * 代码传递
         * 过滤器
         * 回调
         * */

        /*
         * 排序
         *
         * 可以使用 JDK 自带的 java.util.Arrays 类对数组进行排序
         *
         * Arrays.sort 默认是升序排列
         * 把比较小的元素放左边
         * 把比较大的元素放右边
         *
         * compare 的返回值
         * 等于 0
         * o1 == o2
         * 大于 0
         * o1 > o2
         * 小于 0
         * o1 < o2
         * */
        {
            Integer[] array = {33, 22, 11, 77, 66, 99};
            Arrays.sort(array);
            // [11, 22, 33, 66, 77, 99]
            System.out.println(Arrays.toString(array));

            Arrays.sort(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            // [99, 77, 66, 33, 22, 11]
            System.out.println(Arrays.toString(array));
        }

        /*
         * 方法引用(Method Reference)
         *
         * 如果 Lambda 中的内容仅仅是调用某个方法，可以使用方法引用(Method Reference)来简化
         *
         * 引用静态方法
         * ClassName::staticMethodName
         *
         * 引用特定对象的实例方法
         * ObjectName::instanceMethodName
         *
         * 引用特定类型的任意对象的实例方法
         * ClassName::methodName
         *
         * 引用构造方法
         * ClassName::new
         *
         * 引用当前类中定义的实例方法
         * this::instanceMethodName
         *
         * 引用父类中定义的实例方法
         * super::instanceMethodName
         * */

        /*
         * 引用特定类型的任意对象的实例方法
         * */
        {
            String[] strings = {"Jack", "james", "Apple", "abort"};
            Arrays.sort(strings, (s1, s2) -> s1.compareToIgnoreCase(s2));
            // [abort, Apple, Jack, james]
            System.out.println(Arrays.toString(strings));

            Arrays.sort(strings, String::compareToIgnoreCase);
            // [abort, Apple, Jack, james]
            System.out.println(Arrays.toString(strings));
        }

        /*
         * 引用数组的构造方法
         * */
        {
            Testable3 t1 = v -> new int[v];
            // 3
            System.out.println(((int []) t1.test(3)).length);

            Testable3 t2 = int[]::new;
            // 3
            System.out.println(((int []) t2.test(3)).length);
        }
    }
}
