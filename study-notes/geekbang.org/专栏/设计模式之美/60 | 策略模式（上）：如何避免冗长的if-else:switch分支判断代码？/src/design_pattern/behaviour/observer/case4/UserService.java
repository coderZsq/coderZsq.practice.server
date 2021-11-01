package design_pattern.behaviour.observer.case4;

public class UserService {
    public long register(String telephone, String password) {
        return Long.valueOf(telephone + password);
    }
}
