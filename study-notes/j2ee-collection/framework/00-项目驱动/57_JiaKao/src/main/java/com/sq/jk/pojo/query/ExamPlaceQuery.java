package com.sq.jk.pojo.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPlaceQuery extends KeywordQuery {
    private Integer provinceId;
    private Integer cityId;
}
