package com.coderZsq.rbac.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    private Long id;
    private String name;
    private String sn;

    public String getJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("sn", sn);
        return JSONUtils.toJSONString(map);
    }
}
