package com.sq.dp.designpattern.templatemethod;

import java.util.List;

public interface IBaseDao<T> {
    public void save(T obj);

    public void update(T obj);

    public void delete(Long id);

    public T get(Long id);

    public List<T> list();
}
