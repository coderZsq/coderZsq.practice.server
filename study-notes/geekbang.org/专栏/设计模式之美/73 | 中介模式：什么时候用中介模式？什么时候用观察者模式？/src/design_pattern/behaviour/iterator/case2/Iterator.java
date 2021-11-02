package design_pattern.behaviour.iterator.case2;

// 接口定义方式二
public interface Iterator<E> {
    boolean hasNext();
    E next();
}