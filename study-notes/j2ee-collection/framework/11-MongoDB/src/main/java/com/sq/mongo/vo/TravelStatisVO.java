package com.sq.mongo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class TravelStatisVO {

    private int month;  //月份
    private double sum;
    private double avg;
    private double min;
    private double max;
    private List<String> dests = new ArrayList<>();
}
