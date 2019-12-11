package com.coderZsq;

public class Main {

    // main方法 (函数 -> 方法), 程序的入口
    public static void main(String[] args) {
        /*
         * 语法须知
         *
         * 每一条语句都必须以分号;结尾
         * Java中的方法, 就是其他编程语言中的函数
         *
         * 程序的入口是main方法
         * 没有main方法, Java程序是无法启动的
         * 方法必须包含在class内部, 先有class, 再有方法
         *
         * public class的名称必须要和文件夹保持一致
         * */
        System.out.println("1只羊");
        System.out.println("2只羊");

        /*
         * 关于左大括号 { 的位置
         * */

        // 推荐
        // public static main(String[] args) {
        //
        // }

        // 不推荐
        // public static main(String[] args)
        // {
        //
        // }

        /*
         * 注释 (Comment)
         *
         * 单行注释
         * 多行注释
         * 文档注释 (一种特殊的多行注释)
         * */

        // 多行注释并不能嵌套多行注释
        // /*
        //  这是一段多行注释
        //  /*
        //   这也是一段多行注释
        //   */
        //  */

        // 这句代码的作用是向屏幕输出一段话
        System.out.println("这是我的第一个Java程序");

        /*
         这句代码的作用是
         向屏幕输出
         一段话
         */
        System.out.println("这是我的第一个Java程序");

        /**
         * 计算2个整数的和
         * @param a 第1个整数
         * @param b 第2个整数
         * @return 2个整数的和
         */
        // public static int add(int a, int b) {
        //    return a + b;
        // }

        /*
         * 数据类型
         *
         * Java 的数据类型主要分为 2 大类
         * 基本类型(Primitive Type)
         * byte:8-bit 的整数，取值范围是 [ 128, 127]
         * short:16-bit 的整数，取值范围是 [–32768, 32767]
         * int:32-bit 的整数，取值范围是 [–231, 231 – 1]
         * long:64-bit 的整数，取值范围是 [–263, 263 – 1]
         * float:单精度 32-bit IEEE 754 浮点数，取值范围是 [1.40E–45F, 3.4028235E38F]
         * double:双精度 64-bit IEEE 754 浮点数，取值范围是 [4.9E-324, 1.7976931348623157E308] ✓boolean:布尔类型，有 true、false 两个取值
         * char:单个 16-bit 的 Unicode 字符
         *
         * 引用类型(Reference Type)
         * 引用类型的值是对对象的引用
         * */

        /*
         * 字面量 (Literal)
         * */

        // 整数
        {
            // 十进制
            byte v1 = 123;
            // 二进制 (或者0B11001)
            short v2 = 0b011001;
            // 十六进制 (或者0XF78A, 0XF78a)
            int v3 = 0xF78A;
            // 以用L或者l结尾表示long类型 (或者199l)
            long v4 = 199L;
        }
        // 浮点数
        {
            // 以F或者f结尾表示float类型 (或者123.4f)
            float v1 = 123.4F;
            // 以D或者d结尾表示double类型 (或者123.4d)
            double v2 = 123.4D;
            // 默认就是double类型
            double v3 = 123.4;
            // 可以用科学计数法 (E或者e)
            float v4 = 1.234E2F;
            double v5 = 1.234e2;
        }
        // 字符和字符串
        {
            // 用单引号表示字符
            char v1 = 'A';
            // 用双引号表示字符串
            String v2 = "ABCD";
        }
        // 布尔
        {
            boolean v1 = true;
            boolean v2 = false;
        }
        // 空值
        {
            String string = null;
        }

        /*
         * 转义序列(Escape Sequences)
         *
         * \b(退格，\u0008)
         * \t(制表符，\u0009)
         * \n(换行，\u000a)
         * \f(换页，\u000c)
         * \r(回车，\u000d)
         * \"(双引号，\u0022)
         * \'(单引号，\u0027)
         * \\(反斜杠，\u005c)
         * */

        /*
         * 在数字中使用下划线
         * */

        // 从Java7开始, 可以给数字添加下划线增强可读性
        {
            int v1 = 1_0000_0000;
            int v2 = 0xFF_EC_DE_5E;
            int v3 = 0b11010010_01101001_10010100_10010010;
            double v4 = 1.23_45_67;
            long v5 = 1___0000_0000;
        }
        // 下面的用法是错误的
        {
            // 不能在浮点数的小数点前后使用下划线
            // double r1 = 1._23;
            // double r2 = 1_.23;
            // 不能在数字的前后使用下划线
            // int r3 = _123;
            // int r4 = 123_;
            // 不能在X, B, F, D, L, E等特殊字母的前后使用下划线
            // byte r5 = 0x_12;
            // byte r6 = 0_b10010;
            // float r7 = 1.23F_;
            // long r8 = 189_L;
        }

        /*
         * 变量的初始化
         *
         * 任何变量在使用之前都必须要先初始化 (赋值)
         * 局部变量: 需要程序员手动初始化
         * 非局部变量(实例变量, 类变量): 编译器会自动给未初始化的变量设置一个初始值
         * */

        /* 运算符 (Operator)
         *
         * 当多个优先级一样的运算符一起使用时
         * 按照结合性进行运算
         *
         * 只有赋值运算符的结合性是从右至左
         * 其他运算符的结合性都是从左至右
         *
         * 为了保证运算符按照预期执行, 尽量多使用小括号
         * 比如 5 * ((a + b) / c)
         *
         * 算数表达式的结果必须被使用
         * */

        /*
         * 字符串拼接
         *
         * 可以使用加号 (+) 进行字符串的拼接
         * */
        {
            int age = 18;
            String name = "Jack";
            double height = 1.78;
            System.out.println(
                    "My name is " + name
                            + ", age is " + age
                            + ", height is " + height
            );
        }

        /*
         * 位运算
         *
         * >> 与 >>>
         * >> (有符号右移): 最左用符号位补齐
         * >>> (无符号右移): 最左用0补齐
         *
         * &, |, ^也能用在boolean类型上
         * 对比&&, ||, &, | 少了短路功能
         * */
        {
            System.out.println(Integer.toBinaryString(-128));
            // -128       = 11111111111111111111111110000000
            System.out.println(Integer.toBinaryString(-128 >> 2));
            // -128 >>  2 = 11111111111111111111111110000000
            System.out.println(Integer.toBinaryString(-128 >>> 2));
            // -128 >>> 2 = 00111111111111111111111110000000

            System.out.println(true & true); // true
            System.out.println(false & true); // false
            System.out.println(false & false); // false

            System.out.println(true | true); // true
            System.out.println(true | false); // false
            System.out.println(false | false); // false

            System.out.println(true ^ true); // false
            System.out.println(false ^ false); // false
            System.out.println(true ^ false); // true
        }

        /*
         * 类型转换 (Type Conversion)
         *
         * 拓宽基本类型转换 (Widening Primitive Conversion)
         * 数据范围小的转为数据范围大的(19种), 可以自动转换(隐式转换)
         * byte 转 short、int、long、float、double
         * short 转 int、long、float、double
         * char 转 int、long、float、double
         * int 转 long、float、double
         * long 转 float、double
         * float 转 double
         * */
        {
            byte b = 12;
            short s = b;
            int i1 = s;

            char c = 'A';
            int i2 = c;

            long l = i1;
            float f = l;
            double d = f;
        }

        /*
         * 类型转换 (Type Conversion)
         *
         * 窄化基本类型转换(Narrowing Primitive Conversion)
         * 数据范围大的转为数据范围小的(22种)，可能会丢失精度和范围，需要强制转换 ✓short 转 byte、char
         * char 转 byte、short
         * int 转 byte、short、char
         * long 转 byte、short、char、int
         * float 转 byte、short、char、int、long
         * double 转 byte、short、char、int、long、float
         * */
        {
            short s = 512;
            char c = (char) s;
            byte b = (byte) c;

            double d = 1.23;
            float f = (float) d;
            int i = (int) d;
        }

        /*
         * 一元数字提升 (Unary Numeric Promotion)
         *
         * 一元数字提升:将 byte、short、char 类型的一元数字自动提升为 int 类型(拓宽基本类型转换)
         *
         * 下面的情况会执行一元数字提升
         * 数组的索引、创建数组时的数组长度
         * 一元运算符 +
         * 一元运算符 –
         * 按位取反(~)
         * 位移(<<、>>、>>>)
         * */
        {
            char c1 = 'A';
            System.out.println(c1); // A
            System.out.println(+c1); // 65
            // char c2 = +c1; error
            char c3 = 65;
        }

        /*
         * 二元数字提升 (Binary Numeric Promotion)
         *
         * 二元数字提升:提升一个或者两个数字(拓宽基本类型转换)
         * 如果任意一个数字是 double 类型，那么另一个就会被转换为 double 类型
         * 否则，如果任意一个数字是 float 类型，那么另一个就会被转换为 float 类型
         * 否则，如果任意一个数字是 long 类型，那么另一个就会被转换为 long 类型
         * 否则，两个数字都被转换为 int 类型
         *
         * 下面的情况会执行二元数字提升
         * 乘(*)、除(/)、取余(%)
         * 加法(+)、减法(–)
         * 比较(<、<=、>、>=)
         * 判等(==、!=)
         * 位运算(&、^、|)
         * 三目(? :)
         * */
        {
            byte v1 = 1;
            byte v2 = 2;
            // byte v3 = v1 + v2; error
            // byte v4 = v1 + 2; error
            // byte v5 = 1 + v2; error
            byte v6 = 1 + 2; // ok
        }
        {
            byte v1 = 1;
            // v1 + v1 + 2; error
            v1 += 2; // ok (符合赋值运算自带转换)
        }

        /*
         * 关键字 (Keyword)
         *
         * 关键字，也叫做保留字(reserved word)
         * Java 的关键字如下所示 abstract、continue、for、new、switch、assert、default、goto、package、synchronized
         * boolean、do、if、private、this、break、double、implements、protected、throw
         * byte、else、import、public、throws、case、enum、instanceof、return、transient
         * catch、extends、int、short、try、char、final、interface、static、void class、finally、long、strictfp、volatile、const、float、native、super、while
         *
         * 虽然goto、const 未被使用，但也属于关键字
         *
         * true、false、null 不是关键字，是字面量
         * */

        /*
         * 标识符 (Identifier)
         *
         * 标识符:变量名、方法名、类名等，命名规则如下
         * 1 不限长度的 Java 字母、Java 数字序列，但必须以 Java 字母开头(区分大小写)
         * 2 不能使用关键字
         * 3 不能使用字面量true、false、null
         *
         * Java 字母
         * Character.isJavaIdentifierStart 方法返回 true 的字符
         * 包括 ASCII 中的 A~Z、a~z，美元符($)，下划线(_)，中文，韩文，日文等字符
         *
         * Java 字母 或者 Java 数字
         * Character.isJavaIdentifierPart 方法返回 true 的字符
         * Java 数字 包括 ASCII 中的 0~9
         * */
        {
            System.out.println(Character.isJavaIdentifierPart('泉'));
        }

        /*
         * 命名建议
         *
         * 变量名、方法名:小驼峰，比如 myNameAndAge
         * 类名:大驼峰，比如 MyNameAndAge
         * 常量:比如 MY_NAME_AND_AGE
         * */

        /*
         * Java 语言规范
         * https://docs.oracle.com/javase/specs/index.html
         * https://docs.oracle.com/javase/specs/jls/se13/html/index.html
         * https://docs.oracle.com/javase/specs/jls/se8/html/index.html
         *
         * Java 虚拟机规范
         * https://docs.oracle.com/javase/specs/jvms/se13/html/index.html
         * https://docs.oracle.com/javase/specs/jvms/se8/html/index.html
         *
         * Java 教程
         * https://docs.oracle.com/javase/tutorial/java/index.html
         *
         * Java API 文档
         * https://docs.oracle.com/en/java/javase/13/docs/api/index.html
         * https://docs.oracle.com/javase/8/docs/api/
         * */
    }

}
