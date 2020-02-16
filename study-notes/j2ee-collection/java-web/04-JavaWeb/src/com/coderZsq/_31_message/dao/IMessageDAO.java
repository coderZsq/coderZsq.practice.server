package com.coderZsq._31_message.dao;

import com.coderZsq._31_message.domain.Message;

import java.util.List;

public interface IMessageDAO {
    void add(Message obj);
    List<Message> getAll();
}
