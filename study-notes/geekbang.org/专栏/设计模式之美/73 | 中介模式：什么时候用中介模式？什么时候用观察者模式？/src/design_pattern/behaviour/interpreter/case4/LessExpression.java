package design_pattern.behaviour.interpreter.case4;

import java.util.Map;

public class LessExpression implements Expression {
    public LessExpression(String strExpr) {
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        return false;
    }
}
