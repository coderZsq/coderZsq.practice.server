package com.sq.dp.designpattern.mediator;

import java.util.Date;

public class User extends IUser {
    public User(String name) {
        this.name = name;
    }

    @Override
    void send(String receiver, String message) {
        server.sendTo(this, message, receiver);
    }

    @Override
    void receive(String sender, String message) {
        System.out.println(new Date().toString() + " [ " + this.getName() + " ] " + sender + " 发来消息: " + message);
    }
}
