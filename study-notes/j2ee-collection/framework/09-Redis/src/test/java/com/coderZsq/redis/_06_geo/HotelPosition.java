package com.coderZsq.redis._06_geo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class HotelPosition {
    // 经度
    private double lng;
    // 纬度
    private double lat;
    // 名字
    private String name;
}
