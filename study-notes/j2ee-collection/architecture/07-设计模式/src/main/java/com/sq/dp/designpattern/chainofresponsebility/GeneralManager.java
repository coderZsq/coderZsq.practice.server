package com.sq.dp.designpattern.chainofresponsebility;

import java.util.Random;

/**
 * 具体处理者: 总经理, 当批假天使>7天时由总经理决定
 */
public class GeneralManager extends Handler {
    public GeneralManager(String name) {
        super(name);
    }

    @Override
    public boolean handle(Request request) {
        Integer leaveDays = request.getLeaveDays();
        Integer allowLeaveDays = new Random().nextInt(20);
        System.out.println("向总经理请假, 总经理心里预期最多请" + allowLeaveDays + "天假");

        if (leaveDays > allowLeaveDays) {
            return false;
        }

        return true;
    }
}
