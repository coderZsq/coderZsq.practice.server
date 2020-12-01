package com.sq.jk.pojo.po;

import java.math.BigDecimal;
import java.util.Date;

public class BalanceDetail {
    private Long id;

    private Date createdTime;

    private Long userId;

    private BigDecimal detail;

    private String info;

    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getDetail() {
        return detail;
    }

    public void setDetail(BigDecimal detail) {
        this.detail = detail;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}