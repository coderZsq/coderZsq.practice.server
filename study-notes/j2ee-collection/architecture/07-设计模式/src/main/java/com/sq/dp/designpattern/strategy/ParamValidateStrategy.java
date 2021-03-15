package com.sq.dp.designpattern.strategy;

/**
 * 抽象策略: 参数校验策略类
 */
public interface ParamValidateStrategy {
    String validate(Object param);
}
