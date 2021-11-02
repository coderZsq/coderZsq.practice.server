package design_pattern.behaviour.iterator.case7;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    // TODO: 成员变量、私有函数等随便你定义

    @Override
    public void add(E obj) {
        //TODO: 由你来完善
    }

    @Override
    public void remove(E obj) {
        // TODO: 由你来完善
    }

    @Override
    public Iterator<E> iterator() {
        return new SnapshotArrayIterator(this);
    }

    public void addAll(ArrayList<E> arrayList) {
    }

    public int size() {
        return 0;
    }

    public E get(int cursor) {
        return null;
    }
}