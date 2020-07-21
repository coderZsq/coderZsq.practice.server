package com.coderZsq.bean;

public class Customer {
    private Integer id;
    private String name;
    private Integer realAge;
    private Double height;

    public Customer() {
    }

    public Customer(String name, Integer realAge, Double height) {
        this.name = name;
        this.realAge = realAge;
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRealAge() {
        return realAge;
    }

    public void setRealAge(Integer realAge) {
        this.realAge = realAge;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + realAge +
                ", height=" + height +
                '}';
    }
}
