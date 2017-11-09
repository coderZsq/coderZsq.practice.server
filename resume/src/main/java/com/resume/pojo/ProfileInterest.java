package com.resume.pojo;

public class ProfileInterest {
    private Integer id;

    private String interest;

    public ProfileInterest(Integer id, String interest) {
        this.id = id;
        this.interest = interest;
    }

    public ProfileInterest() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }
}