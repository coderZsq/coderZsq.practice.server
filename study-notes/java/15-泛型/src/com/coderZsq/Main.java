package com.coderZsq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*
         * 泛型(Generics)
         *
         * 从 Java 5 开始，增加了泛型技术
         * 什么是泛型?
         * 将类型变为参数，提高代码复用率
         * 建议的类型参数名称
         * T :Type
         * E :Element
         * K :Key
         * N :Number
         * V :Value
         * S、U、V :2nd, 3rd, 4th types
         * */

        /*
         * 泛型类型(Generic Type)
         *
         * 什么是泛型类型?
         * 使用了泛型的类或者接口
         * 比如
         * java.util.Comparator
         * java.util.Comparable
         * */
        {
            // Java 7以前的写法
            // Student<String> stu1 = new Student<String>();

            // 从Java 7开始, 可以省略右边<>中的类型
            Student<String> stu1 = new Student<>();
            stu1.setScore("A");
            String score1 = stu1.getScore();

            Student<Double> stu2 = new Student<>();
            stu2.setScore(98.5);
            Double score2 = stu2.getScore();
        }

        /*
         * 多个类型参数
         * */
        {
            Student2<String, String> s1 = new Student2<>("E9527", "A++");
            Student2<Integer, Double> s2 = new Student2<>(18, 96.5);
        }

        /*
         * 泛型类型的继承
         * */
        {
            Box<String> strBox = new Box<>();
            Box<Integer> intBox = new Box<>();
            Box<Object> objBox = new Box<>();
//            strBox = intBox; // error
//            objBox = strBox; // error

            // 如果上面代码正确的话, 请思考下面代码
            objBox.setElement(new Object());
            // 将Object直接转成String?
            String str = strBox.getElement();
        }

        {
            // public interface Collection<E> extends Iterable<E>
            // public interface List<E> extends Collection<E>
            // public class ArrayList<E> extends List<E>
        }

        {
            Iterable<String> it = null;
            Collection<String> col = null;
            List<String> li = null;
            ArrayList<String> al = null;

            it = col;
            col = li;
            li = al;
        }

        {
            List<Object> list = null;
            ArrayList<String> al = null;

//            list = al; // error
        }

        {
            List<String> li = null;
            MyList<String, Integer> ml1 = null;
            MyList<String, Double> ml2 = null;
            MyList<String, String> ml3 = null;

            li = ml1;
            li = ml2;
            li = ml3;
        }
    }
}
