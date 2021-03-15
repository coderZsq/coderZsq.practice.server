package com.sq.dp.designpattern.flyweight;

/**
 * 客户端
 */
public class PanClient {
    private String id;
    private User user;
    private PanServer server;

    public PanClient(String id, PanServer server) {
        this.id = id;
        this.server = server;
        server.connect(this);
    }

    public void login(User user) {
        System.out.println("用户: " + user.getUsername() + " 登录了...");;
        this.user = user;
    }

    public void upload(LocalFile localFile) {
        server.uploadFile(user, localFile);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PanServer getServer() {
        return server;
    }

    public void setServer(PanServer server) {
        this.server = server;
    }
}
