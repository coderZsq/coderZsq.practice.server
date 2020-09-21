package com.nonghang.trans.mapper;


import org.apache.ibatis.annotations.*;

@Mapper
public interface TransMapper {
    @Update("update t_account set amount=amount+#{amount} where id=#{indId}")
    public void transIn(@Param("indId") Integer inId, @Param("amount") Integer amount );


    @Insert(" insert into t_trans_log(in_id,amount,trans_id) " +
            "values(#{inId},#{amount},#{transId})")
    public int transInLog(Integer inId, Integer amount, String transId);

    @Select("select count(*) from t_trans_log where trans_id=#{transId}")
    int queryTransLog(String transId);
}
