package design_principles.dry.case5;

public class UserRepo {
    public boolean checkIfUserExisted(String email, String password) {
        //...query db to check if email&password exists
        return false;
    }

    public User getUserByEmail(String email) {
        //...query db to get user by email...
        return null;
    }
}
