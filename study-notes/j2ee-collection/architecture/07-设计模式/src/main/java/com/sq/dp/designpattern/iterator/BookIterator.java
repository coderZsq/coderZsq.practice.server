package com.sq.dp.designpattern.iterator;

import java.util.List;

/**
 * 具体迭代器
 */
public class BookIterator implements Iterator<Book> {
    private List<Book> books = null;
    private int index = -1;

    public BookIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public Book first() {
        int FIRSET_INDEX = 0;
        return books.get(FIRSET_INDEX);
    }

    @Override
    public Book next() {
        if (this.hasNext()) {
            return books.get(++index);
        }
        return null;
    }

    @Override
    public boolean hasNext() {
        return index < books.size() - 1;
    }
}
