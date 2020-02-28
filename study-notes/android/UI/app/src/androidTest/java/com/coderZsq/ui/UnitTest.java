package com.coderZsq.ui;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.coderZsq.ui.unittest.UnitTestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UnitTest {
    // 测试方法

    /**
     * 注意: 测试方法没有返回值, 测试方法没有参数传递
     */
    @Test
    public void testAdd() {
        int add = UnitTestUtils.add(3, 5);
        // 判断拿到的值是都是想要的
        /**
         * 参数1: 自己期望看到的值
         * 参数2: 调用方法得到的值
         * 如果期望的值和得到的值是相等的, 说明这个方法的逻辑是正常的
         * 也就是这次测试是成功的
         */
        assertEquals(8, add);
    }

    @Test
    public void testMul() {
        int mul = UnitTestUtils.mul(4, 5);
        /**
         * 断言
         */
        assertEquals(20, mul);
    }

    @Test
    public void testString() {
        UnitTestUtils utils = new UnitTestUtils();
        assertEquals("哈哈哈", utils.getString());
    }
}
