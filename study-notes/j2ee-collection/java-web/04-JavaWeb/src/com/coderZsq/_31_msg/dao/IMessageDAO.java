package com.coderZsq._31_msg.dao;

import com.coderZsq._31_msg.domain.Message;

import java.util.List;

public interface IMessageDAO {
    void add(Message obj);
    List<Message> getAll();
}
