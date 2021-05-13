package cn.wolfcode.netty.im.webserver.user.controller;

import cn.wolfcode.netty.im.constant.Constants;
import cn.wolfcode.netty.im.server.session.SessionManager;
import cn.wolfcode.netty.im.util.UserContext;
import cn.wolfcode.netty.im.webserver.base.controller.BaseController;
import cn.wolfcode.netty.im.webserver.user.model.LoginData;
import cn.wolfcode.netty.im.webserver.user.model.WebSessionVo;
import cn.wolfcode.netty.im.webserver.user.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户帐号
 *
 * @author Leon
 */
@RestController
@RequestMapping("/user/account")
public class UserAccountController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(UserAccountController.class);

    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService, SessionManager sessionManager) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping("/login")
    public Object login(String username, String password, HttpServletRequest req) {
        try {
            LoginData loginData = userAccountService.login(username, password);
            // 保存当前登录用户信息
            UserContext.setCurrentUser(loginData.getUser());
            return putMsgToJsonString(Constants.WebSite.SUCCESS, "登录成功", loginData);
        } catch (Exception e) {
            log.warn("[登录] 登录失败，请查看原因：", e);
            return putMsgToJsonString(Constants.WebSite.ERROR, e.getMessage());
        }
    }

    /**
     * 获取当前的会话列表（所有在线用户）
     */
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions() {
        List<WebSessionVo> sessions = userAccountService.getSessions();
        return putMsgToJsonString(Constants.WebSite.SUCCESS, sessions);
    }

    /**
     * 根据 id 获取会话信息（包括历史消息）
     */
    @RequestMapping(value = "/sessions/{sessionId}", method = RequestMethod.GET)
    public Object getSessionById(@PathVariable("sessionId") String sessionId) {
        WebSessionVo session = userAccountService.getSessionById(sessionId);
        return putMsgToJsonString(Constants.WebSite.SUCCESS, session);
    }
}
