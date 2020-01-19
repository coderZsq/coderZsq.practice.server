package com.coderZsq;

import java.io.Serializable;

public class Book implements Serializable {
    private double price;
    private String name;
    public Book(double price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
