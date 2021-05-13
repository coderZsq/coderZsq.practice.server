package cn.wolfcode.netty.im.webserver.user.model;

/**
 * @author Leon
 */
public class LoginData {

    private String token;
    private UserVo user;

    public LoginData(String token, UserVo user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
