package com.coderZsq;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void calculate() {
        area = width * height;
        girth = 2 * (width + height);
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 10);
        rectangle.show();
//        area is 100.0
//        girth is 40.0
    }
}
