package com.coderZsq;

public class Student2<N, S> {
    private N no;
    private S score;

    public Student2(N no, S score) {
        this.no = no;
        this.score = score;
    }

    public Student2() {

    }

    public void setNo(N no) {
        this.no = no;
    }

    public void setScore(S score) {
        this.score = score;
    }
}
