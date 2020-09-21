package com.gonghang.trans.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface TransMapper {

    @Update("update t_account set amount=amount-#{amount} where id=#{outId} and amount > #{amount} ")
    public int transOut(@Param("outId") Integer outId, @Param("amount") Integer amount);

    @Insert(" insert into t_trans_log(out_id,in_id,amount,trans_id) " +
            "values(#{outId},#{inId},#{amount},#{transId})")
    public int transOutLog(Integer outId, Integer inId, Integer amount, String transId);

    @Select("select count(*) from t_trans_log where trans_id=#{transId}")
    int queryTransLog(String transId);
}
