package com.coderZsq;

public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    protected void calculate() {
        double half = Math.PI * radius;
        area  = half * radius;
        girth = half * 2;
    }

    public static void main(String[] args) {
        Circle circle = new Circle(10);
        circle.show();
//        area is 314.1592653589793
//        girth is 62.83185307179586
    }
}
