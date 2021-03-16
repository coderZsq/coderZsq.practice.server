package com.sq.dp.designpattern.state;

/**
 * 具体状态: 不及格
 */
public class PoorState extends State {
    public PoorState(Student student) {
        this(student, 0);
    }

    public PoorState(State state) {
        this(state.getStudent(), state.getScore());
    }

    public PoorState(Student student, int score) {
        this(student, "不及格", score);
    }

    public PoorState(Student student, String name, int score) {
        super.student = student;
        super.name = name;
        super.score = score;
    }

    @Override
    public void check() {
        if (super.score >= 90) {
            super.student.setState(new ExcellentState(this));
        } else if (super.score >= 60) {
            super.student.setState(new NormalState(this));
        }
    }
}
