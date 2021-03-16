package com.sq.dp.designpattern.iterator;

public class Client {
    public static void main(String[] args) {
        Book book1 = new Book(1L, "《设计模式》", "GoF");
        Book book2 = new Book(2L, "《Java 编程思想》", "Bruce Eckel");
        Book book3 = new Book(3L, "《深入理解计算机系统》", "Bryant O'Hallaron");
        Book book4 = new Book(4L, "《深入理解 Java 虚拟机》", "周志明");

        IBookShelf bookShelf = new BookShelf();
        bookShelf.add(book1);
        bookShelf.add(book2);
        bookShelf.add(book3);
        bookShelf.add(book4);

        System.out.println("Java 相关的书籍: ");
        Iterator<Book> iterator = bookShelf.getIterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println("书籍: " + book.getName() + "\t作者: " + book.getAuthor());
        }
    }
}
