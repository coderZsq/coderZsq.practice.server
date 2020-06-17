package com.sq.mongo;

import com.sq.mongo.vo.TravelStatisVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class AggregateTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Date getDate(int year, int month, int day) {
        Calendar instance = Calendar.getInstance();

        instance.set(year, month, day);
        return instance.getTime();
    }

    @Test
    public void testAggr() {
        //2019年每月份平均人均消费金额
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("travelTime")
                        .gte(this.getDate(2019, 0, 1)).lt(this.getDate(2020, 0, 1))),

                Aggregation.project("travelTime").andExpression("{$month:'$travelTime'}")
                        .as("travelMonth").andInclude("destName", "perExpend"),

                Aggregation.group("travelMonth")
                        .sum("perExpend").as("sum")
                        .avg("perExpend").as("avg")
                        .min("perExpend").as("min")
                        .max("perExpend").as("max")
                        .push("destName").as("dests"),

                Aggregation.project().and("_id").as("month").andInclude("sum", "avg", "min", "max", "dests"),

                Aggregation.sort(Sort.Direction.ASC, "month")

        );
        AggregationResults<TravelStatisVO> aggregationResults =
                mongoTemplate.aggregate(aggregation, "travel", TravelStatisVO.class);
        for (TravelStatisVO aggregationResult : aggregationResults) {
            System.out.println(aggregationResult);
        }
    }
}
