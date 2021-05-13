package cn.wolfcode.netty.im.webserver.user.service.impl;

import cn.wolfcode.netty.im.server.model.Session;
import cn.wolfcode.netty.im.server.session.SessionManager;
import cn.wolfcode.netty.im.util.UserContext;
import cn.wolfcode.netty.im.webserver.exception.BusinessException;
import cn.wolfcode.netty.im.webserver.user.mapper.UserAccountMapper;
import cn.wolfcode.netty.im.webserver.user.model.*;
import cn.wolfcode.netty.im.webserver.user.service.UserAccountService;
import cn.wolfcode.netty.im.webserver.user.service.UserInfoService;
import cn.wolfcode.netty.im.webserver.user.service.UserMessageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author Leon
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountMapper userAccountMapper;
    private final UserInfoService userInfoService;
    private final UserMessageService userMessageService;
    private final SessionManager sessionManager;

    public UserAccountServiceImpl(SessionManager sessionManager, UserAccountMapper userAccountMapper, UserInfoService userInfoServiceImpl, UserMessageService userMessageServiceImpl) {
        this.sessionManager = sessionManager;
        this.userAccountMapper = userAccountMapper;
        this.userInfoService = userInfoServiceImpl;
        this.userMessageService = userMessageServiceImpl;
    }

    @Override
    public void save(UserAccountEntity userAccount) {
        //判断用户是否已注册
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", userAccount.getAccount());
        UserAccountEntity user = queryObjectByAccount(map);
        if (user == null || user.getId() < 1) {
            userAccountMapper.save(userAccount);
            if (userAccount.getId() != null) {
                //保存基本信息
                UserInfoEntity userInfo = userAccount.getUserInfo();
                userInfo.setUid(userAccount.getId());
                userInfoService.save(userInfo);
            }
        }
    }

    @Override
    public int update(UserAccountEntity userAccount) {
        return userAccountMapper.update(userAccount);
    }

    @Override
    public int delete(Long id) {
        return userAccountMapper.delete(id);
    }

    @Override
    public LoginData login(String username, String password) {
        Map<String, Object> map = new HashMap<>(5);
        map.put("account", password);
        UserAccountEntity userAccountEntity = userAccountMapper.queryObjectByAccount(map);

        if (userAccountEntity == null) {
            throw new BusinessException("登录失败，用户名或密码错误！");
        }

        String passwordInDb = userAccountEntity.getPassword();
        if (!password.equals(passwordInDb)) {
            throw new BusinessException("登录失败，用户名或密码错误！");
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        UserVo userVo = transferLoginUser(userAccountEntity);

        return new LoginData(token, userVo);
    }

    @Override
    public List<WebSessionVo> getSessions() {
        Session[] sessions = sessionManager.getSessions();
        List<WebSessionVo> ret = new ArrayList<>();

        // 当前对话框的 sessionId（也可以理解为索引）
        Map<String, Object> param = new HashMap<>(5);

        UserVo currentUser = UserContext.getCurrentUser();
        if (currentUser == null || sessions.length <= 0) {
            return ret;
        }

        // 获取到当前在线的用户 id 列表，过滤掉当前用户
        List<String> idList = Arrays.stream(sessions)
                .filter((s) -> !s.getAccount().equals(currentUser.getId() + ""))
                .map(Session::getAccount)
                .collect(Collectors.toList());

        if (idList.size() <= 0) {
            return ret;
        }

        List<UserVo> userList = userInfoService.queryByIdList(idList);

        for (UserVo user : userList) {
            WebSessionVo webSession = new WebSessionVo(user.getId() + "", user);

            String currentUserId = currentUser.getId();
            String userId = user.getId();

            // 获取当前用户与 session 用户之间的聊天记录
            param.put("sendUser", currentUserId);
            param.put("receiveUser", userId);

            List<UserMessageEntity> messageList = userMessageService.getHistoryMessageList(param);
            if (messageList != null && messageList.size() > 0) {
                webSession.setMessages(messageList);
            }

            ret.add(webSession);
        }
        return ret;
    }

    @Override
    public WebSessionVo getSessionById(String sessionId) {
        Session session = sessionManager.getSession(sessionId);
        UserVo currentUser = UserContext.getCurrentUser();

        if (currentUser == null || session == null) {
            return null;
        }

        List<UserVo> userList = userInfoService.queryByIdList(Collections.singletonList(sessionId));
        if (userList.size() > 0) {
            UserVo user = userList.get(0);

            WebSessionVo webSession = new WebSessionVo(sessionId, user);

            // 获取当前用户与 session 用户之间的聊天记录
            Map<String, Object> param = new HashMap<>(5);
            param.put("sendUser", currentUser.getId());
            param.put("receiveUser", user.getId());

            List<UserMessageEntity> messageList = userMessageService.getHistoryMessageList(param);
            if (messageList != null && messageList.size() > 0) {
                webSession.setMessages(messageList);
            }

            return webSession;
        }

        return null;
    }

    private UserVo transferLoginUser(UserAccountEntity userAccountEntity) {
        String name = userAccountEntity.getAccount();
        UserInfoEntity userInfo = userAccountEntity.getUserInfo();
        UserVo user = new UserVo();

        if (userInfo != null) {
            name = !StringUtils.isEmpty(userInfo.getNickname()) ? userInfo.getNickname() : userInfo.getName();
            user.setAvatar(userInfo.getProfilePhoto());
            user.setSex(userInfo.getSex());
            user.setSignature(userInfo.getSignature());
        }

        user.setName(name);
        user.setId(userAccountEntity.getId() + "");
        return user;
    }

    @Override
    public UserAccountEntity queryObjectByAccount(Map<String, Object> map) {
        return userAccountMapper.queryObjectByAccount(map);
    }
}
