package cn.wolfcode.netty.im.webserver.user.service.impl;

import cn.wolfcode.netty.im.webserver.user.mapper.UserMessageMapper;
import cn.wolfcode.netty.im.webserver.user.model.UserMessageEntity;
import cn.wolfcode.netty.im.webserver.user.service.UserMessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author Leon
 */
@Service
public class UserMessageServiceImpl implements UserMessageService {

    private final UserMessageMapper userMessageMapper;

    public UserMessageServiceImpl(UserMessageMapper userMessageMapper) {
        this.userMessageMapper = userMessageMapper;
    }

    @Override
    public void save(UserMessageEntity userMessage) {
        userMessageMapper.save(userMessage);
    }

    @Override
    public int update(UserMessageEntity userMessage) {
        return userMessageMapper.update(userMessage);
    }

    @Override
    public int delete(Long id) {
        return userMessageMapper.delete(id);
    }

    @Override
    public List<UserMessageEntity> getHistoryMessageList(Map<String, Object> map) {
        return userMessageMapper.getHistoryMessageList(map);
    }

}
