package com.coderZsq.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Department {
    private Long id;
    private String sn;
    private String name;

    public String getJsonString() {
        return "";
    }
}
