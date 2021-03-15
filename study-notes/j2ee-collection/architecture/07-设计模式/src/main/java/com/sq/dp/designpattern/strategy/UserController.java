package com.sq.dp.designpattern.strategy;

public class UserController {
    public void handleData(Object data, ParamValidateStrategy strategy) {
        strategy.validate(data);
        // TODO...
    }
}
