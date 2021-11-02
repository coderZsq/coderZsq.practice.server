package design_pattern.behaviour.iterator.case7;

import java.util.Iterator;

public class SnapshotArrayIterator<E> implements Iterator<E> {
    public <E> SnapshotArrayIterator(ArrayList eArrayList) {

    }

    // TODO: 成员变量、私有函数等随便你定义

    @Override
    public boolean hasNext() {
        // TODO: 由你来完善
        return false;
    }

    @Override
    public E next() {//返回当前元素，并且游标后移一位
        // TODO: 由你来完善
        return null;
    }
}