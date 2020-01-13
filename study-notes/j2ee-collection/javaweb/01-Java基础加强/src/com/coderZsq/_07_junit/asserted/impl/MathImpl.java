package com.coderZsq._01_junit.asserted.impl;

import com.coderZsq._01_junit.asserted.IMath;

public class MathImpl implements IMath {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }
}
