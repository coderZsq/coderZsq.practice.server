package com.sq.jk.pojo.po;

import lombok.Data;

@Data
public class PlateRegion {

    private Integer id;
    //名称
    private String name;
    //车牌
    private String plate;
    //拼音
    private String pinyin;

    private Integer parentId;
}