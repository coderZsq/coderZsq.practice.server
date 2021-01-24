package com.sq.jk.common.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Streams {
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }
}
