package com.sq.dp.designpattern.chainofresponsebility;

/**
 * 请求: 封装请假相关信息
 */
public class Request {
    private String emp;
    private String type;
    private Integer leaveDays;

    public Request(String emp, String type, Integer leaveDays) {
        this.emp = emp;
        this.type = type;
        this.leaveDays = leaveDays;
    }

    public String getEmp() {
        return emp;
    }

    public void setEmp(String emp) {
        this.emp = emp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }
}
