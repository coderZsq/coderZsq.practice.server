package reconfiguration.sepc.case1;

import java.util.Calendar;
import java.util.Date;

public class Demo {
    private Date date;

    // 重构前的代码
    public void invest(long userId, long financialProductId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return;
        }
        //...
    }

    // 重构后的代码：提炼函数之后逻辑更加清晰
    public void invest2(long userId, long financialProductId) {
        if (isLastDayOfMonth(new Date())) {
            return;
        }
        //...
    }

    public boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }
}
