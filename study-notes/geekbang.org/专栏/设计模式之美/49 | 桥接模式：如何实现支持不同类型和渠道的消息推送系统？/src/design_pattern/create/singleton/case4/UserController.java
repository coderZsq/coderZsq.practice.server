package design_pattern.create.singleton.case4;

// Logger类的应用示例：
public class UserController {
    public UserController() {
    }

    public void login(String username, String password) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log(username + " logined!");
    }
}