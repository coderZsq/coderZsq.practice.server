package design_pattern.behaviour.iterator.case4;

import design_pattern.behaviour.iterator.case1.Iterator;

public class ArrayList<E> implements List<E> {
    public int modCount;

    //...
    @Override
    public Iterator iterator() {
        return new ArrayIterator(this);
    }

    @Override
    public void add(E xzg) {

    }

    @Override
    public void remove(E a) {
        
    }

    public int size() {
        return 0;
    }

    public E get(int cursor) {
        return null;
    }
    //...省略其他代码
}
