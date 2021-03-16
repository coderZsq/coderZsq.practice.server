package com.sq.dp.designpattern.state;

/**
 * 具体状态: 优秀状态
 */
public class ExcellentState extends State {
    public ExcellentState(State state) {
        super.student = state.getStudent();
        super.name = "优秀";
        super.score = state.getScore();
    }

    @Override
    public void check() {
        if (super.score < 60) {
            super.student.setState(new PoorState(this));
        } else if (super.score < 90) {
            super.student.setState(new NormalState(this));
        }
    }
}
