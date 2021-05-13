package cn.wolfcode.netty.im.webserver.user.service;

import cn.wolfcode.netty.im.webserver.user.model.LoginData;
import cn.wolfcode.netty.im.webserver.user.model.UserAccountEntity;
import cn.wolfcode.netty.im.webserver.user.model.WebSessionVo;

import java.util.List;
import java.util.Map;

/**
 * 用户帐号
 *
 * @author Leon
 */
public interface UserAccountService {

    UserAccountEntity queryObjectByAccount(Map<String, Object> map);

    void save(UserAccountEntity userAccount);

    int update(UserAccountEntity userAccount);

    int delete(Long id);

    LoginData login(String username, String password);

    List<WebSessionVo> getSessions();

    WebSessionVo getSessionById(String sessionId);
}
