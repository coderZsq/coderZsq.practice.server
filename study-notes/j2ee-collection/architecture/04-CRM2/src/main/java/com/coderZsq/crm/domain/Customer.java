package com.coderZsq.crm.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;


/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2020-03-26 21:45:35
 */
@Data
public class Customer implements Serializable {
    public static final Integer GENDER_MALE = 1;//男
    public static final Integer GENDER_FEMALE = 0;//女
    public static final Integer STATUS_POTENTIAL = 0;//潜在客户
    public static final Integer STATUS_POOL = 1;//转正式客户
    public static final Integer STATUS_FAIL = 2;//移入客户池
    public static final Integer STATUS_FORMAL = 3;//开发失败
    public static final Integer STATUS_LOST = 4;//客户流失
    private static final long serialVersionUID = 261970347249643549L;
    private Long id;
    private String name;
    private Integer age;
    private Integer gender = GENDER_MALE;
    private String tel;
    private String qq;
    private Date inputTime;
    private Integer status = STATUS_POTENTIAL; //直接使用类型的常量来描述
    //需要显示一个对象的其他信息--> 使用对象关联更合适
    private SystemDictionaryItem job;
    private SystemDictionaryItem source;
    private Employee seller;
    private Employee inputUser;


    public String getDisplayGender() {
        return gender == 0 ? "女" : "男";
    }

    public String getDisplayStatus() {
        switch (status) {
            case 0:
                return "潜在客户";
            case 1:
                return "正式客户";
            case 2:
                return "客户池";
            case 3:
                return "开发失败";
            case 4:
                return "客户流失";
        }
        return null;
    }

    public String getJsonString() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("name", name);
        hashMap.put("age", age);
        hashMap.put("gender", gender);
        hashMap.put("tel", tel);
        hashMap.put("qq", qq);
        hashMap.put("inputTime", inputTime);
        hashMap.put("status", status);
        hashMap.put("jobId", job == null ? "" : job.getId());
        hashMap.put("sourceId", source == null ? "" : source.getId());
        hashMap.put("sellerId", seller == null ? "" : seller.getId());
        hashMap.put("sellerName", seller == null ? "" : seller.getName());
        hashMap.put("inputUserId", inputUser == null ? "" : inputUser.getId());
        return JSON.toJSONString(hashMap);
    }
}