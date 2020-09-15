package com.nonghang.trans.service;

public interface ITransService {

    /**
     * 农行转入操作
     * @param inId 转入的Id
     * @param amount 金额
     */
    public void tranIn(Integer inId,Integer amount,String stranId);
}
