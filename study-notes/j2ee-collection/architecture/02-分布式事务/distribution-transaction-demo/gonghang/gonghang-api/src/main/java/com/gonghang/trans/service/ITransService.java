package com.gonghang.trans.service;

public interface ITransService {

    /**
     * 金额转出接口  调用本地数据库修改用户账户信息
     * @param outId  转出人Id
     * @param amount  转出金额
     */
    public int transOut(Integer outId, Integer amount);

    /**
     * 用户调用的api
     * @param inId  转入的账号
     * @param outId 转出的账号
     * @param amount 金额
     */
    public void trans(Integer inId,Integer outId, Integer amount);

    /**
     * 转出交易记录
     * @param outId 转出账号id
     * @param inId  转入账号id
     * @param amount  转账金额
     * @param transId  交易id
     */
    void transOutLog(Integer outId, Integer inId, Integer amount, String transId);

    /**
     * 查询交易记录
     * @param transId 交易id
     * @return
     */
    int queryTransLog(String transId);
}
