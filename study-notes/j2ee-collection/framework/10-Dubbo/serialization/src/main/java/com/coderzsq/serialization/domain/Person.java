package com.coderzsq.serialization.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Person implements Serializable {
    private String sex;
}
