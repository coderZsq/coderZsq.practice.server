package com.sq.resume.bean.base;

import java.util.Date;

public abstract class DateBean extends BaseBean {
    private Date beginDay;
    private Date endDay;

    public Date getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(Date beginDay) {
        this.beginDay = beginDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }
}
