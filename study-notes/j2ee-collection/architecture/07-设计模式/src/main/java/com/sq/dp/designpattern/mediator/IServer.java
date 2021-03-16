package com.sq.dp.designpattern.mediator;

/**
 * 抽象中介: 负责用户注册和消息的转发
 */
public interface IServer {
    void register(IUser user);

    void sendTo(IUser from, String message, String to);
}
