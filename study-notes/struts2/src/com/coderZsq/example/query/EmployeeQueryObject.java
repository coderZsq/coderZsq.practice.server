package com.coderZsq.example.query;

import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQueryObject {
    @Getter @Setter
    private String name; // 关键字
    @Getter @Setter
    private BigDecimal minSalary; // 最低工资
    @Getter @Setter
    private BigDecimal maxSalary; // 最高工资

    private List<String> conditions = new ArrayList<>(); // 条件查询
    private List<Object> params = new ArrayList<>(); // 占位符参数

    // 返回查询的SQL, 如: WHERE name LIKE ? AND salary > ? AND salary < ?
    public String getQuery() {
        if (hasLength(name)) {
            conditions.add("name LIKE ?");
            params.add("%" + name + "%");
        }
        if (minSalary != null) {
            conditions.add("salary >= ?");
            params.add(minSalary);
        }
        if (maxSalary != null) {
            conditions.add("salary <= ?");
            params.add(maxSalary);
        }
        if (conditions.size() == 0) {
            return "";
        }
        return " WHERE " + StringUtils.join(conditions, " AND ");
    }

    // 返回占位符参数值
    public List<Object> getParameters() {
        return params;
    }

    public boolean hasLength(String str) {
        return str != null && !"".equals(str.trim());
    }
}
