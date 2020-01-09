package com.coderZsq.rbac.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PageResult<T> {
    private boolean success = true;
    private T data;
    private String message;

    public PageResult(T data) {
        this.data = data;
    }

    public static <T> PageResult success(T data) {
        return new PageResult(data);
    }

    public static <T> PageResult error(String message) {
        PageResult pageResult = new PageResult<>();
        pageResult.setSuccess(false);
        pageResult.setMessage(message);
        return pageResult;
    }
}
