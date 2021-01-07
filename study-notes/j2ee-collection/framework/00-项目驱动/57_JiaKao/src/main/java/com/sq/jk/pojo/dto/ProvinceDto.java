package com.sq.jk.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProvinceDto {
    private Integer id;
    private String name;
    private String plate;
    private List<CityDto> children;
}
