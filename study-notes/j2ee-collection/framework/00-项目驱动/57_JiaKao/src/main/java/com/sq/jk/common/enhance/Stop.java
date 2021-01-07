package com.sq.jk.common.enhance;

import lombok.Data;

@Data
public class Stop<T> {
    private T data;

    public static <T> Stop<T> create() {
        return new Stop<>();
    }

    public static <T> Stop<T> create(T data) {
        Stop<T> stop = new Stop<>();
        stop.setData(data);
        return stop;
    }
}
