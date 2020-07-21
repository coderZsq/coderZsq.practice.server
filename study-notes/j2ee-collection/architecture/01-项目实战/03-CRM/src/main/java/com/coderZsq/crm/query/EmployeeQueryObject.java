package com.coderZsq.crm.query;

import lombok.Data;

@Data
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private long deptId = -1;
}