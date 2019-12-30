package com.coderZsq;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*
         * 开发中的错误
         *
         * 在开发 Java 程序的过程中，会遇到各种各样的错误
         * 语法错误
         * 会导致编译失败，程序无法正常运行
         *
         * 逻辑错误
         * 比如需要执行加法操作时，不小心写成了减法操作
         *
         * 运行时错误
         * 在程序运行过程中产生的意外，会导致程序终止运行
         * 在 Java 中也叫做异常
         *
         * 程序产生了异常, 一般称之为: 抛出了异常
         * 如果没有主动去处理它, 会导致程序终止运行
         *
         * 如果【代码2】抛出了异常, 并且没有主动去处理它
         * 程序就会退出, 【代码3】将没有机会执行
         * */
        {
            代码1;
            代码2;
            代码3;
        }

        /*
         * 思考: 下面代码的打印结果是什么?
         * */
        {
            System.out.println(1);
            Integer i1 = new Integer("123");
            System.out.println(2);
            Integer i2 = new Integer("abc");
            System.out.println(3);

            // 由于"abc"无法转换成整数， new Integer("abc")会抛出一个异常
            // 异常类型: java.lang.NumberFormatException
            // 由于没有主动去处理这个异常，所以导致程序终止运行
            // 打印结果是:1、2
        }

        {
            System.out.println(1);
            Integer i = new Integer("1234");
            Object obj = "12.34";
            Double d = (Double) obj;
            System.out.println(2);

            // 由于"12.34"无法强转成Double类型，(Double) obj会抛出一个异常
            // 异常类型: java.lang.ClassCastExceptiointn
            // 由于没有主动去处理这个异常，所以导致程序终止运行
            // 打印结果:1
        }

        {
            Integer[] nums = {11, null, 22};
            for (int num : nums) {
                System.out.println(num);
            }

            // Integer在自动拆箱为int时，会调用Integer对象的intValue() 方法
            // 由于 num[1]为null, 使用null调用方法会抛出一个异常
            // 异常类型: java.lang.NullPointerException
            // 由于没有主动去处理这个异常，所以导致程序终止运行
            // 打印结果:11

            Integer[] nums = {11, null, 22};
            // 建议的写法
            for (Integer num : nums) {
                System.out.println(num);
            }
            // 打印结果: 11, null, 22
        }

        /*
         * 打印的细节
         * */
        {
            Dog dog1 = new Dog();
            // Dog - 666
            System.out.println(dog1);

            Dog dog2 = null;
            // null
            System.out.println(dog2);

            public void println (Object x){
            String s = String.valueOf(x);
            synchronized (this) {
                print(s);
                newLine();
            }
        }

            public static String valueOf (Object obj){
            return (obj == null) ? "null" : obj.toString();
        }
        }

        /*
         * 异常的处理
         *
         * 程序产生了异常，一般我们会称之为:抛出了异常
         *
         * 不管抛出的是检查型异常，还是非检查型异常
         * 只要程序员没有主动去处理它，都会导致 Java 程序终止运行
         *
         * 异常有 2 种处理方法
         * try-catch
         * 捕获异常
         *
         * throws
         * 将异常往上抛
         * */

        /*
         * try-catch
         *
         * 如果【代码2】没有抛出异常
         * 1【代码1、3】都会被执行
         * 2 所有的 catch 都不会被执行
         * 3【代码4】会被执行
         *
         * 如果【代码2】抛出异常
         * 1【代码1】会被执行、【代码3】不会被执行
         * 2 会选择匹配的 catch 来执行代码
         * 3【代码4】会被执行
         *
         * 父类型的异常必须写在子类型的后面
         *【异常A】不可以是【异常B、C】的父类型
         *【异常B】不可以是【异常C】的父类型
         * */
        {
            try {
                // 代码1
                // 代码2 (可能会抛出异常)
                // 代码3
            } catch (异常A e) {
                // 当抛出[异常A]类型, 会进入这个代码块

            } catch (异常B e) {
                // 当没有抛出[异常A]类型
                // 但抛出[异常B]类型的异常时, 会进入这个代码块

            } catch (异常C e) {
                // 当没有抛出[异常A], [异常B]类型
                // 但抛出[异常C]类型的异常时, 会进入这个代码块
            }
            // 代码4
        }

        /*
         * 思考: 下面代码的打印结果是什么?
         * */
        {
            System.out.println(1);
            Integer i = new Integer("abc");
            System.out.println(2);

            // 打印结果是: 1
        }

        {
            System.out.println(1);
            try {
                System.out.println(2);
                Integer i = new Integer("abc");
                System.out.println(3);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println(4);
            }
            System.out.println(5);

            // 打印结果是: 1, 2, 4, 5
        }

        /*
         * 一个catch捕获多种类型的异常
         *
         * 从 Java 7 开始，单个 catch 可以捕获多种类型的异常
         *
         * 如果并列的几个异常类型之间存在父子关系，保留父类型即可
         *
         * 这里的变量 e 是隐式 final 的
         * */
        {
            try {

            } catch (异常A | 异常B | 异常C e) {
                // 当抛出[异常A]或[异常B]或[异常C]类型的异常时, 会进入这个代码块
            }
        }

        /*
         * 异常对象的常用方法
         * */
        {
            try {
                Integer i = new Integer("abc");
            } catch (NumberFormatException e) {
                // 异常描述
                System.out.println(e.getMessage());
                // 异常名称 + 异常描述
                System.out.println(e);
                // 打印堆栈信息
                e.printStackTrace();
            }
        }


        /*
         * finally
         *
         * try 或 catch 正常执行完毕后，一定会执行 finally 中的代码
         * finally 可以和 try-catch 搭配使用，也可以只和 try 搭配使用
         * 经常会在 finally 中编写一些关闭、释放资源的代码(比如关闭文件)
         * */
        {
            try {

            } catch (异常 e) {

            } finally {

            }
        }

        {
            try {

            } finally {

            }
        }

        {
            PrintWriter out = null;
            try {
                out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
                out.print("My name is Castie!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }

        /*
         * finally 细节
         *
         * 如果在执行 try 或 catch 时，JVM 退出或者当前线程被中断、杀死
         * finally 可能不会执行
         *
         * 如果 try 或 catch 中使用了 return、break、continue 等提前结束语句
         * finally 会在 return、break、continue 之前执行
         * */

        /*
         * 思考:下面代码的打印结果是什么?
         * */
        {
            for (int i = 1; i <= 3; i++) {
                try {
                    System.out.println(i + "_try_1");
                    if (i == 2) continue;
                    System.out.println(i + "_try_2");
                } finally {
                    System.out.println(i + "_finally");
                }
            }

            // 打印结果是
            // 1_try_1
            // 1_try_2
            // 1_finally
            // 2_try_1
            // 2_finally
            // 3_try_1
            // 3_try_2
            // 3_finally
        }

        {
            for (int i = 1; i <= 3; i++) {
                try {
                    System.out.println(i + "_try_1");
                    if (i == 2) break;
                    System.out.println(i + "_try_2");
                } finally {
                    System.out.println(i + "_finally");
                }
            }

            // 打印结果是
            // 1_try_1
            // 1_try_2
            // 1_finally
            // 2_try_1
            // 2_finally
        }

        {
            System.out.println(get());
            // 打印结果是: 3, 5, 4
        }

        /*
         * 异常的种类
         *
         * Java 中所有的异常最终都继承自 java.lang.Throwable
         *
         * 检查型异常(Checked Exception)
         * 这类异常一般难以避免，编译器会进行检查
         * 如果开发者没有处理这类异常，编译器将会报错
         * 哪些异常是检查型异常?
         * 除 Error、RuntimeException 以外的异常
         *
         * 非检查型异常(Unchecked Exception)
         * 这类异常一般可以避免，编译器不会进行检查
         * 如果开发者没有处理这类异常，编译器将不会报错 哪些异常是非检查型异常?
         * Error、RuntimeException
         * */

        /*
         * 常见的检查型异常
         * */
        {
            // java.io.FileNotFoundException, 文件不存在
            FileOutputStream fos = new FileOutputStream("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        }

        {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            // java.text.ParseException, 字符串格式不对
            Date date = fmt.parse("2066/06/06");
        }

        {
            // java.lang.InterruptedException
            Thread.sleep(1000);
        }

        {
            // java.lang.ClassNotFoundException, 不存在这个类
            Class cls = Class.forName("Dog");
            // java.lang.InstantiationException, 没有无参构造方法
            // java.lang.IllegalAccessException, 没有权限访问构造方法
            Dog dog = (Dog) cls.newInstance();
        }

        /*
         * 常见的非检查型异常 - Error
         * */
        {
            for (int i = 0; i < 200; i++) {
                // java.lang.OutOfMemoryError, 内存不够用
                long[] a = new long[1000000000];
            }
        }

        {
            // java.lang.StackOverflowError, 栈内存溢出
            test();
        }

        /*
         * 常见的非检查型异常 - RuntimeException
         * */
        {
            // java.lang.NullPointerException, 使用了空指针
            StringBuilder s = null;
            s.append("abc");
        }

        {
            // java.lang.NumberFormatException, 数字的格式不对
            Integer i = new Integer("abc");
        }

        {
            int[] array = {11, 22, 33};
            // java.lang.ArrayIndexOutOfBoundsException, 数组的索引越界
            array[4] = 44;
        }

        {
            Object obj = "123.4";
            // java.lang.ClassCastException, 类型不匹配
            Double d = (Double) obj;
        }

        /*
         * throws 示例
         *
         * 异常从 method3 方法中产生，一路向上抛
         * 最后在 main 方法中使用 try-catch 处理了异常
         * */
        {
            try {
                method1();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        /*
         * 使用异常的好处
         *
         * 将错误处理代码与普通代码区分开
         *
         * 能将错误信息传播到调用堆栈中
         *
         * 能对错误类型进行区分和分组
         * */

        {
            int age = 10;
            Asserts.test(age > 0);

            String name = "";
            Asserts.test(name != null && name.length() != 0);
        }
    }

    public static void test() {
        test();
    }

    static int get() {
        try {
            new Integer("abc");
            System.out.println(1);
            return 2;
        } catch (Exception e) {
            System.out.println(3);
            return 4;
        } finally {
            System.out.println(5);
        }
    }

    /*
     * throws 的流程
     *
     * 如果异常最终抛给了 JVM
     * 那么整个 Java 程序将终止运行
     * */

    /*
     * throws
     *
     * throws 的作用:将异常抛给上层方法
     * 如果throws 后面的异常类型存在父子关系，保留父类型即可
     * */
    static void test1() throws FileNotFoundException, ClassNotFoundException {
        PrintWriter out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        Class cls = Class.forName("Dog");
    }

    static void test2() throws Exception {
        PrintWriter out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        Class cls = Class.forName("Dog");
    }

    static void test3() throws Throwable {
        PrintWriter out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        Class cls = Class.forName("Dog");
    }

    /*
     * throws 示例
     *
     * 可以一部分异常使用try-catch 处理，另一部分异常使用throws 处理
     * */
    static void test4() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        try {
            Class cls = Class.forName("Dog");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void method3() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/java/13-异常");
        out.println("My name is Castie!");
        out.close();
    }

    static void method2() throws FileNotFoundException {
        method3();
    }

    static void method1() throws FileNotFoundException {
        method2();
    }
}
