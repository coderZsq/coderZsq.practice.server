package com.sq.dp.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

public class BookShelf implements IBookShelf {
    private List<Book> books = new ArrayList<>();

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void remove(Book book) {
        books.remove(book);
    }

    @Override
    public Iterator<Book> getIterator() {
        return new BookIterator(this.books);
    }
}
