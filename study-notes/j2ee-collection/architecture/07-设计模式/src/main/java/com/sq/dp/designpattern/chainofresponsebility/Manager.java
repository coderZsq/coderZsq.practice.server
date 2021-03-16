package com.sq.dp.designpattern.chainofresponsebility;

import java.util.Random;

/**
 * 具体处理者: 经理类, 最大批假权限为7天
 */
public class Manager extends Handler {
    public Manager(String name) {
        super(name);
    }

    @Override
    public boolean handle(Request request) {
        Integer leaveDays = request.getLeaveDays();
        Integer allowLeaveDays = new Random().nextInt(10);
        System.out.println("向经理请假, 经理心里预期最多请" + allowLeaveDays + "天假");

        if (leaveDays > allowLeaveDays) {
            return false;
        }

        int MAX_ALLOWED_DAYS  = 7;
        boolean result = leaveDays <= MAX_ALLOWED_DAYS;

        if (result) {
            return result;
        }

        return next.handle(request);
    }
}
