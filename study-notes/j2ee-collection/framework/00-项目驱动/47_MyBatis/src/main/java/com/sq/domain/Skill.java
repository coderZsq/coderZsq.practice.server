package com.sq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Integer id;
    private Date createdTime;
    private String name;
    private Integer level;
}
