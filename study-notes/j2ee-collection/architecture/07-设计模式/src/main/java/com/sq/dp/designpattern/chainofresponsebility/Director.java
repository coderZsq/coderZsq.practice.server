package com.sq.dp.designpattern.chainofresponsebility;

import java.util.Random;

/**
 * 具体处理者: 主管类, 可以决定员工是否能请假, 最大批假权限3天
 */
public class Director extends Handler {
    public Director(String name) {
        super(name);
    }

    @Override
    public boolean handle(Request request) {
        Integer leaveDays = request.getLeaveDays();
        // 主管心里认为你最多可以请几天假
        Integer allowLeaveDays = new Random().nextInt(20);
        System.out.println("向主管请假, 主管心里预期最多请" + allowLeaveDays + "天假");

        if (leaveDays > allowLeaveDays) {
            return false;
        }

        int MAX_ALLOWED_DAYS  = 3;
        boolean result = leaveDays <= MAX_ALLOWED_DAYS;

        if (result) {
            return result;
        }

        return next.handle(request);
    }
}
