package com.sq.dp.designpattern.state;

/**
 * 抽象状态
 */
abstract public class State {
    protected Student student;
    protected String name;
    protected int score;

    public abstract void check();

    public void addScore(int score) {
        this.score += score;
        System.out.print("加上: " + score + "分, \t当前分数: " + this.score);
        this.check();
        System.out.println("分, \t当前状态: " + student.getState().getName());
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
