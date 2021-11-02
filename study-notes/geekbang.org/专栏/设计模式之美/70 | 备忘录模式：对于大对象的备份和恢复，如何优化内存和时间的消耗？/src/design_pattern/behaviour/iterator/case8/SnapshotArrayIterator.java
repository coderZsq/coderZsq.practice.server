package design_pattern.behaviour.iterator.case8;

import design_pattern.behaviour.iterator.case7.ArrayList;

import java.util.Iterator;

public class SnapshotArrayIterator<E> implements Iterator<E> {
    private int cursor;
    private ArrayList<E> snapshot;

    public SnapshotArrayIterator(ArrayList<E> arrayList) {
        this.cursor = 0;
        this.snapshot = new ArrayList<>();
        this.snapshot.addAll(arrayList);
    }

    @Override
    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    @Override
    public E next() {
        E currentItem = snapshot.get(cursor);
        cursor++;
        return currentItem;
    }
}