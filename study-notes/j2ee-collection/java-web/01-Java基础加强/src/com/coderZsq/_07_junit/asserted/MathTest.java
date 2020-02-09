package com.coderZsq._07_junit.asserted;

import com.coderZsq._07_junit.asserted.impl.MathImpl;
import org.junit.Assert;
import org.junit.Test;

// Math 测试类
public class MathTest {

    // 依赖关系
    private IMath math = new MathImpl();

    @Test
    public void testAdd() {
        int ret = math.add(1, 2);
        // 使用断言方式
        Assert.assertEquals("断言失败信息", 3, ret);
    }

    @Test
    public void testDivide() {
        int ret = math.divide(12, 3);
        Assert.assertEquals(4, ret);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivide1() {
        int ret = math.divide(12, 0);
    }

    @Test(timeout = 2000)
    public void test123() throws Exception {
        // TODO
    }
}
