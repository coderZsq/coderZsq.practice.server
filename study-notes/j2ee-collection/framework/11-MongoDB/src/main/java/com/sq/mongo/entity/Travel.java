package com.sq.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Document("travel")
public class Travel {
    private int month;
    private double sum;
    private double avg;
    private double min;
    private double max;
    private List<String> dests = new ArrayList<>();
}
