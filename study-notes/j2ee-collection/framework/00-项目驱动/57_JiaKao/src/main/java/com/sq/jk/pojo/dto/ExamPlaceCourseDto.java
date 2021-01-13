package com.sq.jk.pojo.dto;

import com.sq.jk.pojo.po.ExamPlaceCourse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExamPlaceCourseDto extends ExamPlaceCourse {
    private Integer provinceId;
    private Integer cityId;
}
