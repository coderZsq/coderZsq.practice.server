package com.coderZsq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
            System.out.println(((int[]) t1.test(3)).length);

            Testable3 t2 = int[]::new;
            // 3
            System.out.println(((int[]) t2.test(3)).length);
        }

        /*
         * 常用函数式接口
         *
         * java.util.function 包中提供了很多常用的函数式接口
         * Supplier
         * Consumer
         * Predicate
         * Function
         * ......
         * */

        /*
         * Supplier
         * */
        {
            // @FunctionalInterface
            // public interface Supplier<T> {
            //   T get();
            // }
        }

        /*
         * Supplier 应用
         *
         * 有时使用 Supplier 传参，可以避免代码的浪费执行(有必要的时候再执行)
         * */
        {
            String s1 = "A";
            String s2 = "B";
            String s3 = "C";
            // A
            getFirstNotEmptyString(s1, () -> s1 + s2 + s3);
        }

        /*
         * Consumer
         * */
        {
            // @FunctionalInterface
            // public interface Consumer<T> {
            //   void accept(T t);
            //
            //   default Consumer<T> andThen(Consumer<? super T> after);
            // }
        }

        /*
         * Consumer 应用
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            forEach(nums, (n) -> {
                String result = ((n & 1) == 0) ? "偶数" : "奇数";
                System.out.println(n + "是" + result);
            });
        }

        /*
         * Consumer - andThen
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            forEach(nums, (n) -> {
                String result = ((n & 1) == 0) ? "偶数" : "奇数";
                System.out.println(n + "是" + result);
            }, (n) -> {
                String result = ((n % 3) == 0) ? "能" : "不能";
                System.out.println(n + result + "被3整除");
            });
        }

        /*
         * Predicate
         * */
        {
            // @FunctionalInterface
            // public interface Predicate<T> {
            //   boolean test(T t);
            //
            //   default Predicate<T> and(Predicate<? super T> other);
            //   default Predicate<T> negate();
            //   default Predicate<T> or(Predicate<? super T> other);
            //
            //   static <T> Predicate<T> isEqual(Object targetRef);
            // }
        }

        /*
         * Predicate 应用
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            String str = join(nums, (n) -> (n & 1) == 0);
            // 44_88_66
            System.out.println(str);
        }

        /*
         * Predicate - and
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            String str = join(nums, (n) -> (n & 1) == 0, (n) -> (n % 3) == 0);
            // 66
            System.out.println(str);
        }

        /*
         * Predicate - or
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            String str = join2(nums, (n) -> (n & 1) == 0, (n) -> (n % 3) == 0);
            // 33_44_88_66
            System.out.println(str);
        }

        /*
         * Predicate - negate
         * */
        {
            int[] nums = {11, 33, 44, 88, 77, 66};
            String str = join3(nums, (n) -> (n & 1) == 0);
            // 11_33_77
            System.out.println(str);
        }

        /*
         * Function
         * */
        {
            // @FunctionalInterface
            // public interface Function<T, R> {
            //   R apply(T t);
            //
            //   default <V> Function<V, R> compose(Function<? super V, ? extends T> before);
            //   default <V> Function<T, V> andThen(Function<? super R, ? extends V> after);
            //
            //   static <T> Function<T, T> identity();
            // }
        }

        /*
         * Function 应用
         * */
        {
            String[] strs = {"12", "567", "666"};
            int result = sum(strs, Integer::valueOf);
            System.out.println(result);
        }

        /*
         * Function - andThen
         * */
        {
            String[] strs = {"12", "567", "666"};
            int result = sum(strs, Integer::valueOf, (i) -> i % 10);
            // 15
            System.out.println(result);
        }

        /*
         * Function - compose
         * */
        {
            String[] strs = {"12", "567", "666"};
            int result = sum(strs, Integer::valueOf, (i) -> i % 10);
            // 15
            System.out.println(result);
        }
    }

    static String getFirstNotEmptyString(String s1, Supplier<String> s2) {
        if (s1 != null && s1.length() != 0) return s1;
        if (s2 == null) return null;
        String str = s2.get();
        return (str != null && str.length() != 0) ? str : null;
    }

    static void forEach(int[] nums, Consumer<Integer> c) {
        if (nums == null || c == null) return;
        for (int n : nums) {
            c.accept(n);
        }
    }

    static void forEach(int[] nums, Consumer<Integer> c1, Consumer<Integer> c2) {
        if (nums == null || c1 == null || c2 == null) return;
        for (int n : nums) {
            c1.andThen(c2).accept(n);
        }
    }

    static String join(int[] nums, Predicate<Integer> p) {
        if (nums == null || p == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            if (p.test(n)) {
                sb.append(n).append("_");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static String join(int[] nums, Predicate<Integer> p1, Predicate<Integer> p2) {
        if (nums == null || p1 == null || p2 == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            if (p1.and(p2).test(n)) {
                sb.append(n).append("_");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static String join2(int[] nums, Predicate<Integer> p1, Predicate<Integer> p2) {
        if (nums == null || p1 == null || p2 == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            if (p1.or(p2).test(n)) {
                sb.append(n).append("_");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static String join3(int[] nums, Predicate<Integer> p) {
        if (nums == null || p == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int n : nums) {
            if (p.negate().test(n)) {
                sb.append(n).append("_");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static int sum(String[] strs, Function<String, Integer> f) {
        if (strs == null || f == null) return 0;
        int result = 0;
        for (String str : strs) {
            result += f.apply(str);
        }
        return result;
    }

    // 将所有数字的个位数加起来
    static int sum(String[] strs, Function<String, Integer> f1, Function<Integer, Integer> f2) {
        if (strs == null || f1 == null || f2 == null) return 0;
        int result = 0;
        for (String str : strs) {
            result += f1.andThen(f2).apply(str);
        }
        return result;
    }

    // 将所有数字的个位数加起来
    static int sum2(String[] strs, Function<String, Integer> f1, Function<Integer, Integer> f2) {
        if (strs == null || f1 == null || f2 == null) return 0;
        int result = 0;
        for (String str : strs) {
            result += f2.compose(f1).apply(str);
        }
        return result;
    }

}
