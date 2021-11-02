package design_principles.isp.case3;

import java.util.Collection;

public class Demo {
    public Statistics count(Collection<Long> dataSet) {
        Statistics statistics = new Statistics();
        //...省略计算逻辑...
        return statistics;
    }
}
