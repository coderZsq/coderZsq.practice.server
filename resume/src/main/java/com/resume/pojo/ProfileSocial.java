package com.resume.pojo;

public class ProfileSocial {
    private Integer id;

    private String src;

    private String href;

    public ProfileSocial(Integer id, String src, String href) {
        this.id = id;
        this.src = src;
        this.href = href;
    }

    public ProfileSocial() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }
}