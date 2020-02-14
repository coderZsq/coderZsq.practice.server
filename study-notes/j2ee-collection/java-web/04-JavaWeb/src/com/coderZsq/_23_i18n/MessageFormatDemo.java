package com.coderZsq._23_i18n;

import org.junit.Test;

import java.text.MessageFormat;

public class MessageFormatDemo {
    public static void main(String[] args) {
        String pattern = "我是{4},你是{1},他是{0},她是{2},它是{3},他们{5}";
        String str = MessageFormat.format(pattern, "A", "B", "C", "D", "E");
        System.out.println(str);
    }

    @Test
    public void testSql() throws Exception {
        String sql = "SELECT * FROM {0} {1}";
        String ret = MessageFormat.format(sql, "product", "WHERE productName LIKE ?");
        // SELECT * FROM product WHERE productName LIKE ?
        System.out.println(ret);
    }
}
