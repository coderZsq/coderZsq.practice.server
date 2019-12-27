package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 字符串(String)
         *
         * Java 中用 java.lang.String 类代表字符串
         * 底层使用 char[] 存储字符数据，从 Java 9 开始，底层使用 byte[] 存储字符数据
         * 所有字符串字面量(比如"abc")都是 String 类的实例
         * String 对象一旦创建完毕，它的字符内容是不可以修改的
         * */
        {
            String s = "555";
            s += "555";
            s = "666";
            test(s);
            System.out.println(s);
        }

        /*
         * 字符串常量池(String Constant Pool)
         *
         * Java 中有个字符串常量池(String Constant Pool，简称 SCP)
         * 从 Java 7 开始属于堆空间的一部分(以前放在方法区)
         *
         * 当遇到字符串字面量时，会去查看 SCP
         * 如果 SCP 中存在与字面量内容一样的字符串对象 A 时，就返回 A
         * 否则，创建一个新的字符串对象 D，并加入到 SCP 中，返回 D
         * */
        {
            String s1 = "coderZsq";
            String s2 = "coderZsq";
            System.out.println(s1 == s2); // true
        }

        /*
         * 字符串的初始化
         * */
        {
            String s1 = "coderZsq";
            String s2 = new String("coderZsq");
            String s3 = new String(s1);
            String s4 = new String(s2);
            char[] cs = {'c', 'o', 'd', 'e', 'r', 'Z', 's', 'q'};
            String s5 = new String(cs);
            String s6 = new String(s5);
        }
    }

    public static void test(String str) {
        str += "555";
    }
}
