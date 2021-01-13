package com.sq.jk.pojo.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPlaceCourseQuery extends KeywordQuery {
    private Short type;
    private Integer provinceId;
    private Integer cityId;
    private Integer placeId;
}
