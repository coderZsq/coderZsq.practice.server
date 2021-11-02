package design_pattern.behaviour.observer.case2;

public class UserService {
    public long register(String telephone, String password) {
        return Long.valueOf(telephone + password);
    }
}
