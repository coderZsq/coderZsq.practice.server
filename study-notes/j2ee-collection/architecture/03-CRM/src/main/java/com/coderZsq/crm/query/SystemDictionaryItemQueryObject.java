package com.coderZsq.crm.query;


import lombok.Data;

@Data
public class SystemDictionaryItemQueryObject extends QueryObject {
    private Long parentId = -1L;
}