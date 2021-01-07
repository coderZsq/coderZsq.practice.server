package com.sq.jk.pojo.po;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DictType {
    //主键
    private Integer id;
    //名称
    @NotBlank(message = "名称不能为空")
    private String name;
    //值
    @NotBlank(message = "值不能为空")
    private String value;
    //简介
    private String intro;
}