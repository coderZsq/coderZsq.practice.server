package com.sq.resume.bean.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseBean {
    private Integer id;
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // @JsonIgnore
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @JsonIgnore
    public String getJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper.writeValueAsString(this).replace("\"", "'");

        // SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // String beginDayString = fmt.format(beginDay);
        // String endDayString = fmt.format(endDay);
        // return "{name:'" + name + "', intro:'" + intro + "', beginDay:'" + beginDayString + "', endDay:'" + endDayString + "', type:" + type + "}";
    }
}
