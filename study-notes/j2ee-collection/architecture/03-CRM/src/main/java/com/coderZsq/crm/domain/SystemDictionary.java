package com.coderZsq.crm.domain;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import lombok.Data;


/**
 * (Systemdictionary)实体类
 *
 * @author makejava
 * @since 2020-03-27 11:28:46
 */
@Data
public class SystemDictionary implements Serializable {
    private static final long serialVersionUID = -36862248671384215L;
    
    private Long id;
    
    private String sn;
    
    private String title;
    
    private String intro;

    public String getJsonString() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("sn", sn);
        hashMap.put("title", title);
        hashMap.put("intro", intro);
        return JSON.toJSONString(hashMap);
    }
}