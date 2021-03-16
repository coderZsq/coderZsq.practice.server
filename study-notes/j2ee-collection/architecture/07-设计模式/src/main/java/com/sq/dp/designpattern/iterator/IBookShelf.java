package com.sq.dp.designpattern.iterator;

/**
 * 抽象聚合角色
 */
public interface IBookShelf {
    void add(Book book);

    void remove(Book book);

    Iterator<Book> getIterator();
}
