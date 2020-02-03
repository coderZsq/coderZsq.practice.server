package com.coderZsq._04_dql;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DQLTest {
    @Test
    public void test1() throws Exception {
        // 取出list集合中每一个元素
        List<String> list = Arrays.asList("A", "B", "C", "D");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next()); // 获取光标后的元素, 移动光标
        }
    }
}
