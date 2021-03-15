package com.sq.dp.designpattern.strategy;

public class UrlValid implements ParamValidateStrategy {
    @Override
    public String validate(Object param) {
        System.out.println("是否是Url: " + param);
        return param instanceof String ? null : "Url格式错误!";
    }
}
