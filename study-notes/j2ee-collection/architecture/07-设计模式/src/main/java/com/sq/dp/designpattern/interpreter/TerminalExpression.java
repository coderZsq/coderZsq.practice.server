package com.sq.dp.designpattern.interpreter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 具体表达式: 终结符表达式
 */
public class TerminalExpression implements Expression {
    private Set<String> set = new HashSet<>();

    public TerminalExpression(String[] data) {
        set.addAll(Arrays.asList(data));
    }

    @Override
    public boolean interpret(String info) {
        return set.contains(info);
    }
}
