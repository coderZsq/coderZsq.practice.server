package design_pattern.create.singleton.case1;

// Logger类的应用示例：
public class UserController {
    private Logger logger = new Logger();

    public UserController() {
    }

    public void login(String username, String password) {
        // ...省略业务逻辑代码...
        logger.log(username + " logined!");
    }
}