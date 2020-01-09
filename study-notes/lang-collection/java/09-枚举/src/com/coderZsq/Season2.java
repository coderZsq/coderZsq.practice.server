package com.coderZsq;

/*
 * 自定义了构造方法的枚举
 * */
public enum Season2 {
    SPRING(5, 15),
    SUMMER(25, 35),
    FALL(15, 25),
    WINTER(-5, 5);

    private int min;
    private int max;

    Season2(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
