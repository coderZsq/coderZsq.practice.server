package cn.wolfcode.netty.im.util;

import cn.wolfcode.netty.im.webserver.user.model.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Leon
 */
public class UserContext {

    private static final Logger log = LoggerFactory.getLogger(UserContext.class);

    public static final String CURRENT_USER = "current_user";

    public static HttpSession getSession() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
    }

    public static void setCurrentUser(UserVo user) {
        HttpSession session = getSession();
        if (session == null) {
            log.warn("[UserContext] 获取不到当前 session，请检查用户是否登录！");
            return;
        }

        session.setAttribute(CURRENT_USER, user);
    }

    public static UserVo getCurrentUser() {
        HttpSession session = getSession();
        if (session == null) {
            log.warn("[UserContext] 获取不到当前 session，请检查用户是否登录！");
            return null;
        }

        return (UserVo) session.getAttribute(CURRENT_USER);
    }
}
