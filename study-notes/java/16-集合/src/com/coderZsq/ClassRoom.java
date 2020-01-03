package com.coderZsq;

import java.util.Iterator;

public class ClassRoom implements Iterable<String> {
    private String[] students;
    public ClassRoom(String... students) {
        this.students = students;
    }

    @Override
    public Iterator<String> iterator() {
        return new ClassRoomItarator();
    }

    private class ClassRoomItarator implements Iterator<String> {
        private int index;
        @Override
        public boolean hasNext() {
            return index < students.length;
        }

        @Override
        public String next() {
            return students[index++];
        }
    }
}
