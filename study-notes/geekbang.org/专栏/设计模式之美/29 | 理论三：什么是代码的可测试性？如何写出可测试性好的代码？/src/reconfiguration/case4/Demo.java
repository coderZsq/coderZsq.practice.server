package reconfiguration.case4;

import java.util.Date;

public class Demo {
    public long caculateDelayDays(Date dueTime) {
        long currentTimestamp = System.currentTimeMillis();
        if (dueTime.getTime() >= currentTimestamp) {
            return 0;
        }
        long delayTime = currentTimestamp - dueTime.getTime();
        long delayDays = delayTime / 86400;
        return delayDays;
    }
}