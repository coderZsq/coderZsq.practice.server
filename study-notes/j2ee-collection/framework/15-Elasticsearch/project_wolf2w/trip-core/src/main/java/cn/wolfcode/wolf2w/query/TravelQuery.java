package cn.wolfcode.wolf2w.query;


import lombok.Getter;
import lombok.Setter;

/**
* 游记查询参数封装对象
*/
@Setter
@Getter
public class TravelQuery extends  QueryObject{

    private Long destId;
    private int dayType = -1;
    private int travelTimeType = -1;
    private int consumeType = -1;

    private String orderBy = "create_time";
}
