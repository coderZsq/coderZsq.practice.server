package cn.wolfcode.netty.im.webserver.user.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leon
 */
public class WebSessionVo {

    private String id;
    private UserVo user;
    private List<UserMessageEntity> messages = new ArrayList<>();

    public WebSessionVo(){}

    public WebSessionVo(String id, UserVo user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<UserMessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<UserMessageEntity> messages) {
        this.messages = messages;
    }
}
