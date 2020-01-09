package com.coderZsq;

public class Student3<T extends Comparable<T>> implements Comparable<Student3<T>> {
    private T score;
    public Student3(T score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student3<T> s) {
        if (s == null) return 1;
        if (score != null) return score.compareTo(s.score);
        return s.score == null ? 0 : 1;
    }

    @Override
    public String toString() {
        return "Student3{" +
                "score=" + score +
                '}';
    }
}
