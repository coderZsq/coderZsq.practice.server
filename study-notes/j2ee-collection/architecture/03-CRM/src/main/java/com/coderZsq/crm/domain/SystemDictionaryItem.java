package com.coderZsq.crm.domain;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import lombok.Data;


/**
 * (Systemdictionaryitem)实体类
 *
 * @author makejava
 * @since 2020-03-27 11:56:09
 */
@Data
public class SystemDictionaryItem implements Serializable {
    private static final long serialVersionUID = 281442739382247031L;

    private Long id;

    private Long parentId;

    private String title;

    private Integer sequence;

    public String getJsonString(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("parentId", parentId);
        hashMap.put("title", title);
        hashMap.put("sequence", sequence);
        return JSON.toJSONString(hashMap);
    }
}