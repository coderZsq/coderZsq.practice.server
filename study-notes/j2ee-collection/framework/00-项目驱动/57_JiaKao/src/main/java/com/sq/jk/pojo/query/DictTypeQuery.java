package com.sq.jk.pojo.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DictTypeQuery extends KeywordQuery {
    private Integer id;
}
