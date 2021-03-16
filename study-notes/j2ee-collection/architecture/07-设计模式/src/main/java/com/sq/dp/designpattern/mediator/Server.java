package com.sq.dp.designpattern.mediator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 具体中介
 */
public class Server implements IServer {
    private List<IUser> users = new ArrayList<>();

    @Override
    public void register(IUser user) {
        if (!users.contains(user)) {
            users.add(user);
            user.setServer(this);
            System.out.println(new Date().toString() + " [ Server ] " + user.getName() + " 用户注册上来了! ");
        }
    }

    @Override
    public void sendTo(IUser from, String message, String receiver) {
        System.out.println(new Date().toString() + " [ Server ] " + from.getName() + " 发送消息给: " + receiver + " 消息内容: " + message);
        IUser to = getByNames(receiver);
        to.receive(from.getName(), message);
    }

    public IUser getByNames(String name) {
        for (IUser user : users) {
            if (name.equals(user.getName())) {
                return user;
            }
        }
        return null;
    }
}
