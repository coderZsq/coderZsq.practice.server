package com.coderZsq.crm.query;

import lombok.Data;

@Data
public class DepartmentQueryObject {
    private String keyword;
    private Integer currentPage = 1; // 当前页
    private Integer pageSize = 5; // 每页数量
}
