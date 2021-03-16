package com.sq.dp.designpattern.state;

/**
 * 具体状态: 良好状态
 */
public class NormalState extends State {
    public NormalState(State state) {
        super.student = state.getStudent();
        super.name = "良好";
        super.score = state.getScore();
    }

    @Override
    public void check() {
        if (super.score < 60) {
            super.student.setState(new PoorState(this));
        } else if (super.score >= 90) {
            super.student.setState(new ExcellentState(this));
        }
    }
}
