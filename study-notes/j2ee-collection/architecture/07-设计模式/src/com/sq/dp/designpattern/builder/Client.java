package com.sq.dp.designpattern.builder;

public class Client {
    public static void main(String[] args) {
        // Director director = new Director(new ConcreteBuilder());
        // Product product = director.construct();
        Product product = new ConcreteBuilder().build();
        product.display();
    }
}
