package cn.wolfcode.wolf2w.test;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 枚举特点：
 *    1:构造器是私有
 *    2:枚举类定义好之后，实例对象个数固定了
 *    3:剩下的跟普通的类一样
 */
@Getter
public class MyDay2 {
    public static final MyDay2 DAY1 = new MyDay2("day1", 1L);
    public static final MyDay2 DAY2 = new MyDay2("day2", 2L);
    @Setter
    private String prefix;
    @Setter
    private Long time;
    private MyDay2(String prefix, Long time){
        this.prefix = prefix;
        this.time = time;
    }

    public String join(String value){
        System.out.println(value);
        return  value;
    }

}
