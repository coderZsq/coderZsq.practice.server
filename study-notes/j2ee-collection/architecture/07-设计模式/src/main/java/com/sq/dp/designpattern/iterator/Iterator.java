package com.sq.dp.designpattern.iterator;

/**
 * 抽象迭代器
 */
public interface Iterator<T> {
    T first();

    T next();

    boolean hasNext();
}
