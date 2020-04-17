package com.coderZsq.crm.query;

import lombok.Data;

@Data
public class DepartmentQueryObject extends QueryObject {
    private String keyword;

    public String getKeyword() {
        return keyword == null || keyword.trim().length() == 0 ? null : keyword;
    }
}
