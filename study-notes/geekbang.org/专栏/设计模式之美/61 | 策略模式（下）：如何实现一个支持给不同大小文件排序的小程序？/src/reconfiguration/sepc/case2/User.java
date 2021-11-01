package reconfiguration.sepc.case2;

public interface User {
    public User getUser(String username, String telephone, String email);// 拆分成多个函数

    public User getUserByUsername(String username);
    public User getUserByTelephone(String telephone);
    public User getUserByEmail(String email);
}
