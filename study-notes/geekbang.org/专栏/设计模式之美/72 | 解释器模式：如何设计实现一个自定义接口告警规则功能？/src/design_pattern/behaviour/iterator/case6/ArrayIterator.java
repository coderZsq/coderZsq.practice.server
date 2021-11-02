package design_pattern.behaviour.iterator.case6;

import design_pattern.behaviour.iterator.case1.Iterator;
import design_pattern.behaviour.iterator.case4.ArrayList;

import java.util.ConcurrentModificationException;

public class ArrayIterator implements Iterator {
    private int cursor;
    private ArrayList arrayList;
    private int expectedModCount;

    public ArrayIterator(ArrayList arrayList) {
        this.cursor = 0;
        this.arrayList = arrayList;
        this.expectedModCount = arrayList.modCount;
    }

    @Override
    public boolean hasNext() {
        checkForComodification();
        return cursor < arrayList.size();
    }

    @Override
    public void next() {
        checkForComodification();
        cursor++;
    }

    @Override
    public Object currentItem() {
        checkForComodification();
        return arrayList.get(cursor);
    }

    private void checkForComodification() {
        if (arrayList.modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }
}
