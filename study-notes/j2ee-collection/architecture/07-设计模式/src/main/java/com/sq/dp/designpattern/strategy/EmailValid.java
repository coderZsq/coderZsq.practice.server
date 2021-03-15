package com.sq.dp.designpattern.strategy;

public class EmailValid implements ParamValidateStrategy {
    @Override
    public String validate(Object param) {
        System.out.println("是否是邮箱: " + param);
        return param instanceof String ? null : "邮箱格式错误!";
    }
}
