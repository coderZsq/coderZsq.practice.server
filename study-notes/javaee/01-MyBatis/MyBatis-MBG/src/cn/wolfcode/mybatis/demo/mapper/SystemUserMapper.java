package cn.wolfcode.mybatis.demo.mapper;

import cn.wolfcode.mybatis.demo.domain.SystemUser;
import cn.wolfcode.mybatis.demo.domain.SystemUserExample;
import cn.wolfcode.mybatis.demo.domain.SystemUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper {
    long countByExample(SystemUserExample example);

    int deleteByExample(SystemUserExample example);

    int deleteByPrimaryKey(SystemUserKey key);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    List<SystemUser> selectByExample(SystemUserExample example);

    SystemUser selectByPrimaryKey(SystemUserKey key);

    int updateByExampleSelective(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByExample(@Param("record") SystemUser record, @Param("example") SystemUserExample example);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
}