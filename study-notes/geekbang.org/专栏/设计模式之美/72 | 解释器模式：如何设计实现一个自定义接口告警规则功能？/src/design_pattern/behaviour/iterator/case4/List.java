package design_pattern.behaviour.iterator.case4;

import design_pattern.behaviour.iterator.case1.Iterator;

public interface List<E> {
    Iterator iterator();

    void add(E xzg);

    void remove(E a);
    //...省略其他接口函数...
}
