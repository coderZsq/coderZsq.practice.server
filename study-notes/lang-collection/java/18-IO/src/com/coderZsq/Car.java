package com.coderZsq;

import java.io.Serializable;

public class Car implements Serializable {
    private double price;
    private String band;
    public Car(double price, String band) {
        this.price = price;
        this.band = band;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", band='" + band + '\'' +
                '}';
    }
}
