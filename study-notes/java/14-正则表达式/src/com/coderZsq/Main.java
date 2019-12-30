package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 字符串的合法验证
         * 在开发中，经常会对一些字符串进行合法验证
         * 6-18个字符，可使用字母、数字、下划线，需以字母开头
         * */

        /*
         * 自己编写验证逻辑
         * */
        {
            // 必须是6-18个字符 false
            System.out.println(validate("12345"));
            // 必须以字母开头 false
            System.out.println(validate("123456"));
            // true
            System.out.println(validate("vv123_456"));
            // 必须由字母, 数字, 下划线组成 false
            System.out.println(validate("vv123+/?456"));
        }

        /*
         * 使用正则表达式
         *
         * [a-zA-Z]\w{5,17}是一个正则表达式
         * 用非常精简的语法取代了复杂的验证逻辑
         * 极大地提高了开发效率
         *
         * 正则表达式的英文
         * Regex Expression
         *
         * 正则表达式是一种通用的技术，适用于绝大多数流行编程语言
         *
         * // JavaScript中的正则表达式
         * const regex = /[a-zA-Z]\w{5,17}/;
         * regex.test('12345') // false
         * regex.test('123456') // false
         * regex.test('vv123_456') // true
         * regex.test('vv123+/?456') // false
         * */
        {
            String regex = "[a-zA-Z]\\w{5,17}";
            "12345".matches(regex); // false
            "123456".matches(regex); // false
            "vv123_456".matches(regex); // true
            "vv123+/?456".matches(regex); // false
        }

        /*
         * 单字符匹配
         *
         * 语法 含义
         * [abc] a、b、c
         * [^abc] 除了 a、b、c 以外的任意字符
         * [a-zA-Z] 从a到z、从A到Z
         * [a-d[m-p]] [a-dm-p](并集)
         * [a-z&&[def]] d、e、f(交集)
         * [a-z&&[^bc]] [ad-z](差集，从 [a-z] 中减去 [bc])
         * [a-z&&[^m-p]] [a-lq-z](差集，从 [a-z] 中减去 [m-p])
         * */

        /*
         * 单字符匹配
         * */
        {
            // 等价于[b|c|r]at, (b|c|r)at
            String regex = "[bcr]at";
            "bat".matches(regex); // true
            "cat".matches(regex); // true
            "rat".matches(regex); // true
            "hat".matches(regex); // false
        }

        {
            String regex = "[^bcr]at";
            "bat".matches(regex); // false
            "cat".matches(regex); // false
            "rat".matches(regex); // false
            "hat".matches(regex); // true
        }

        {
            String regex = "foo[1-5]";
            "foo3".matches(regex); // true
            "foo6".matches(regex); // false
        }

        {
            String regex = "foo[^1-5]";
            "foo3".matches(regex); // false
            "foo6".matches(regex); // true
        }

        {
            String regex = "foo1-5";
            "foo1-5".matches(regex); // true
            "foo1".matches(regex); // false
            "foo5".matches(regex); // false
        }

        {
            String regex = "[0-4[6-8]]";
            "5".matches(regex); // false
            "7".matches(regex); // true
            "9".matches(regex); // false
        }

        {
            String regex = "[0-9&&[^345]]";
            "2".matches(regex); // true
            "3".matches(regex); // false
            "4".matches(regex); // false
            "5".matches(regex); // false
            "6".matches(regex); // true
        }

        {
            String regex = "[0-9&&[345]]";
            "2".matches(regex); // false
            "3".matches(regex); // true
            "4".matches(regex); // true
            "5".matches(regex); // true
            "6".matches(regex); // false
        }

        /*
         * 预定义字符
         * 语法 含义
         * . 任意字符
         * \d [0-9](数字)
         * \D [^0-9](非数字)
         * \s [ \t\n\f\r](空白)
         * \S [^\s](非空白)
         * \w [a-zA-Z_0-9](单词)
         * \W [^\w](非单词)
         * 以 1 个反斜杠(\)开头的字符会被当做转义字符处理
         * 因此，为了在正则表达式中完整地表示预定义字符，需要以 2 个反斜杠开头，比如"\\d"
         * */

        /*
         * 预定义字符
         * */
        {
            String regex = ".";
            "@".matches(regex); // true
            "c".matches(regex); // true
            "6".matches(regex); // true
            ".".matches(regex); // true
        }

        {
            String regex = "\\.";
            "@".matches(regex); // false
            "c".matches(regex); // false
            "6".matches(regex); // false
            ".".matches(regex); // true
        }

        {
            String regex = "\\[123\\]";
            "1".matches(regex); // false
            "2".matches(regex); // false
            "3".matches(regex); // false
            "[123]".matches(regex); // true
        }

        {
            String regex = "\\d";
            "c".matches(regex); // false
            "6".matches(regex); // true
        }

        {
            String regex = "\\D";
            "c".matches(regex); // true
            "6".matches(regex); // false
        }

        {
            String regex = "\\s";
            "\t".matches(regex); // true
            "\n".matches(regex); // true
            "\f".matches(regex); // true
            "\r".matches(regex); // true
            " ".matches(regex); // true
            "6".matches(regex); // false
        }

        {
            String regex = "\\S";
            "\t".matches(regex); // false
            "\n".matches(regex); // false
            "\f".matches(regex); // false
            "\r".matches(regex); // false
            " ".matches(regex); // false
            "6".matches(regex); // true
        }

        {
            String regex = "\\w";
            "_".matches(regex); // true
            "c".matches(regex); // true
            "6".matches(regex); // true
            "+".matches(regex); // false
        }

        {
            String regex = "\\W";
            "_".matches(regex); // false
            "c".matches(regex); // false
            "6".matches(regex); // false
            "+".matches(regex); // true
        }
    }

    public static boolean validate(String email) {
        // 验证逻辑...
        if (email == null) {
            System.out.println("不能为空");
            return false;
        }
        char[] chars = email.toCharArray();
        if (chars.length < 6 || chars.length > 18) {
            System.out.println("必须是6-18个字符");
            return false;
        }
        if (!isLetter(chars[0])) {
            System.out.println("必须以字母开头");
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (isLetter(c) || isDigit(c) || c == '_') continue;
            System.out.println("必须由字母, 数字, 下划线组成");
            return false;
        }
        return true;
    }

    private static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
