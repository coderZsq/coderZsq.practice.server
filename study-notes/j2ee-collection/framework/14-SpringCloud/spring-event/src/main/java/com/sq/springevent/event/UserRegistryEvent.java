package com.sq.springevent.event;

import com.sq.springevent.domain.UserBean;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

// 定义一个事件类型
@Data
public class UserRegistryEvent extends ApplicationEvent {
    private UserBean userBean;

    public UserRegistryEvent(Object source, UserBean userBean) {
        super(source);
        this.userBean = userBean;
    }
}
