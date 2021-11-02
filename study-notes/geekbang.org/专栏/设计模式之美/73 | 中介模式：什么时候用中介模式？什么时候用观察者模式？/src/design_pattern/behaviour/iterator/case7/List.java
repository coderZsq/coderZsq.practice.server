package design_pattern.behaviour.iterator.case7;

import java.util.Iterator;

public interface List<E> {
    Iterator iterator();

    void add(E e);

    void remove(E e);
    //...省略其他接口函数...
}
