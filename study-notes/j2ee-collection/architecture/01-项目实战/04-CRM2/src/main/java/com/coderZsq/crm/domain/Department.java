package com.coderZsq.crm.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class Department {
    private Long id;
    private String sn;
    @Excel(name = "部门名称_dept", needMerge = true)
    private String name;

    public String getJson() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("sn", sn);
        map.put("name", name);
        // 怎么把mapper对象转换为json
        // 1 可以使用fastjson 2 springmvc内置的jackson
        return JSON.toJSONString(map);
    }
}
