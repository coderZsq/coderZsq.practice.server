package com.sq.jk.test;

public class SqlTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String sql = "INSERT INTO  dict_type(name, value, intro) VALUES ('哈哈%d', 'haha%d', '%d');\n";
            System.out.format(sql, i, i ,i);
        }
    }
}
