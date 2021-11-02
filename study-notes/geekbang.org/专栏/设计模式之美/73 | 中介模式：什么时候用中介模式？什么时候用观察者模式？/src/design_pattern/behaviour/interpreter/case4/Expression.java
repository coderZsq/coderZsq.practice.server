package design_pattern.behaviour.interpreter.case4;

import java.util.Map;

public interface Expression {
    boolean interpret(Map<String, Long> stats);
}
