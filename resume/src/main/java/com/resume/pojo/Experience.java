package com.resume.pojo;

public class Experience {
    private Integer id;

    private String time;

    private String job;

    private String corp;

    private String href;

    private String desc1;

    private String desc2;

    private String desc3;

    public Experience(Integer id, String time, String job, String corp, String href, String desc1, String desc2, String desc3) {
        this.id = id;
        this.time = time;
        this.job = job;
        this.corp = corp;
        this.href = href;
        this.desc1 = desc1;
        this.desc2 = desc2;
        this.desc3 = desc3;
    }

    public Experience() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp == null ? null : corp.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }
}