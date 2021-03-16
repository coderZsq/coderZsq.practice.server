package com.sq.dp.designpattern.mediator;

/**
 * 抽象的同时: 用户间的共同行为接口的定义
 */
abstract public class IUser {
    protected IServer server;
    protected String name;

    abstract void send(String receiver, String message);

    abstract void receive(String sender, String message);

    public IServer getServer() {
        return server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
