package com.coderZsq.rbac.qo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeQueryObject extends QueryObject {
    //关键字
    private String keyword;
    private Long deptId = -1L;//默认全部,所以默认值为-1
}
