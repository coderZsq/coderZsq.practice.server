package cn.wolfcode.netty.im.webserver.user.mapper;

import cn.wolfcode.netty.im.webserver.base.mapper.BaseMapper;
import cn.wolfcode.netty.im.webserver.user.model.UserMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * @author Leon
 */
public interface UserMessageMapper extends BaseMapper<UserMessageEntity> {
    /**
     * 获取历史记录
     *
     * @param map
     * @return
     */
    List<UserMessageEntity> getHistoryMessageList(Map<String, Object> map);

}
