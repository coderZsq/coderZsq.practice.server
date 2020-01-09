package com.coderZsq;

import java.util.List;

public interface MyList<E, T> extends List<E> {
    void setNo(T no);
}
