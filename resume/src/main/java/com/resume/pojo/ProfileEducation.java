package com.resume.pojo;

public class ProfileEducation {
    private Integer id;

    private String major;

    private String school;

    private String year;

    public ProfileEducation(Integer id, String major, String school, String year) {
        this.id = id;
        this.major = major;
        this.school = school;
        this.year = year;
    }

    public ProfileEducation() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }
}