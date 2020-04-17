package com.coderZsq.crm.query;

import lombok.Data;

@Data
public class QueryObject {
    // 通用的参数
    private Integer currentPage = 1; // 当前页
    private Integer pageSize = 5; // 每页数量
}
