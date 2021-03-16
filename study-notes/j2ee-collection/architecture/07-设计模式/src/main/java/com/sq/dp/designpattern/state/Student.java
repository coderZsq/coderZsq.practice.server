package com.sq.dp.designpattern.state;

/**
 * 环境对象
 */
public class Student {
    private State state;

    public Student() {
        state = new PoorState(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void add(int score) {
        state.addScore(score);
    }
}
