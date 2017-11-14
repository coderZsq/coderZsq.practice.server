package com.resume.pojo;

public class Contact {
    private Integer id;

    private String wechatUrl;

    private String wechat;

    private String mobile;

    private String email;

    private String qq;

    public Contact(Integer id, String wechatUrl, String wechat, String mobile, String email, String qq) {
        this.id = id;
        this.wechatUrl = wechatUrl;
        this.wechat = wechat;
        this.mobile = mobile;
        this.email = email;
        this.qq = qq;
    }

    public Contact() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl == null ? null : wechatUrl.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }
}