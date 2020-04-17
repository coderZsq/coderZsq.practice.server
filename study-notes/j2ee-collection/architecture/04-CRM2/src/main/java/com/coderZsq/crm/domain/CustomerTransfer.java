package com.coderZsq.crm.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * (Customertransfer)实体类
 *
 * @author makejava
 * @since 2020-04-01 10:17:34
 */
@Data
public class CustomerTransfer implements Serializable {
    private static final long serialVersionUID = 472468757999873720L;

    private Long id;
    private Date operateTime; // 操作时间
    private String reason; // 原因

    private Customer customer; // 客户
    private Employee oldSeller; // 原销售人员
    private Employee newSeller; // 新销售人员
    private Employee operator; // 操作人
}