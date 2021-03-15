package com.sq.dp.designpattern.flyweight;

/**
 * 具体享元对象: 资源
 */
public class Resource {
    private String hash;
    private LocalFile localFile;
    private User user;

    public Resource(String hash, LocalFile localFile, User user) {
        this.hash = hash;
        this.localFile = localFile;
        this.user = user;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalFile getLocalFile() {
        return localFile;
    }

    public void setLocalFile(LocalFile localFile) {
        this.localFile = localFile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
