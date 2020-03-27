package com.coderZsq.crm.domain;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;


/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2020-03-27 13:47:11
 */
@Data
public class Customer implements Serializable {
    private static final long serialVersionUID = 451061176217623114L;

    public static final Integer GENDER_MALE = 1;//男
    public static final Integer GENDER_FEMALE = 0;//女
    public static final Integer STATUS_POTENTIAL = 0;//潜在客户
    public static final Integer STATUS_POOL = 1;//转正式客户
    public static final Integer STATUS_FAIL = 2;//移入客户池
    public static final Integer STATUS_FORMAL = 3;//开发失败
    public static final Integer STATUS_LOST = 4;//客户流失
    
    private Long id;
    private String name;
    private Integer age;
    private Integer gender = GENDER_MALE;
    private String tel;
    private String qq;
    private Date inputTime;
    private Integer status = STATUS_POTENTIAL; // 直接使用类型的常量来描述
    // 需要显示一个对象的其他信息 --> 使用对象关联更合适
    private SystemDictionaryItem job;
    private SystemDictionaryItem source;
    private Employee seller;
    private Employee inputUser;

    public String getDisplayGender() {
        return gender == 0 ? "女" : "男";
    }

    public  String getDisplayStatus(){
        switch (status){
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
}