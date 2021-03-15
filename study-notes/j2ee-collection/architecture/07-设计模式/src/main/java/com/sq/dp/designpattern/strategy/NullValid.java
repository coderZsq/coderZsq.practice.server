package com.sq.dp.designpattern.strategy;

public class NullValid implements ParamValidateStrategy {
    @Override
    public String validate(Object param) {
        System.out.println("是否为空: " + param);
        return param != null ? null : "参数不能为空!";
    }
}
