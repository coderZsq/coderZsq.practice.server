package com.sq.resume.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sq.resume.bean.base.DateBean;

public class Experience extends DateBean {
    private String job;
    private String intro;
    private Company company;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    // @JsonIgnore
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // public Integer getCompanyId() {
    //     return company.getId();
    // }
}
