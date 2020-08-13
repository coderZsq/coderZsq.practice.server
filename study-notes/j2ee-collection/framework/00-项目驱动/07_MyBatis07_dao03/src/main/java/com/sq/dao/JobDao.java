package com.sq.dao;

import com.sq.bean.Job;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobDao {
    @Select(
            "SELECT j.* FROM job j "
            + "JOIN person_job pj ON j.id = pj.job_id AND pj.person_id = #{personId}"
    )
    List<Job> listByPerson(Integer personId);
}
