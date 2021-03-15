package com.sq.dp.designpattern.flyweight;

/**
 * 非享元对象: 资源链接
 */
public class ResourceLink {
    private String id;
    private String uid;
    private String hash;

    public ResourceLink(String id, String uid, String hash) {
        this.id = id;
        this.uid = uid;
        this.hash = hash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
