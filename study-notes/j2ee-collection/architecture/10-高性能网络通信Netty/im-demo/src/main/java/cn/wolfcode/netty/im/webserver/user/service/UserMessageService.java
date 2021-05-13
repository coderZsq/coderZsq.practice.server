package cn.wolfcode.netty.im.webserver.user.service;

import cn.wolfcode.netty.im.webserver.user.model.UserMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
public interface UserMessageService {

    void save(UserMessageEntity userMessage);

    int update(UserMessageEntity userMessage);

    int delete(Long id);

    /**
     * 获取历史记录
     *
     * @param map
     * @return
     */
    List<UserMessageEntity> getHistoryMessageList(Map<String, Object> map);
}
