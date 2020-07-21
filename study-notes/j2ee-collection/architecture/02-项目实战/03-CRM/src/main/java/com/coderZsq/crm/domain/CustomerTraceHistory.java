package com.coderZsq.crm.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (CustomerTraceHistory)实体类
 *
 * @author makejava
 * @since 2020-03-29 20:54:49
 */
@Data
public class CustomerTraceHistory implements Serializable {
    private static final long serialVersionUID = 938576681226275086L;

    private Long id; //id
    private Object traceTime;//跟进时间
    private String traceDetails;//跟进说明
    private Integer traceResult; //结果
    private String remark; //备注
    private Date inputTime;//时间
    private Integer type = 0;//类型 0 潜在客户 1 正式客户

    private SystemDictionaryItem traceType; //交流方式
    private Employee inputUser;//录入人
    private Customer customer;//客户

    public String getDisplayTraceResult() {
        if (traceResult == 1) {
            return "差";
        } else if (traceResult == 2) {
            return "中";
        } else if (traceResult == 3) {
            return "优";
        }
        return "";
    }

    public String getDisplayType() {
        if (type == 0) {
            return "潜在客户";
        } else if (type == 1) {
            return "正式客户";
        }
        return "";
    }
}