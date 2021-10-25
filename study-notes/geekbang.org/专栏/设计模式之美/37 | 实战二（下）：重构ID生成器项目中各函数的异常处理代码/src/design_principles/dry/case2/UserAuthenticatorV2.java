package design_principles.dry.case2;

public class UserAuthenticatorV2 {

    public void authenticate(String userName, String password) {
        if (!isValidUsernameOrPassword(userName)) {
            // ...throw InvalidUsernameException...
        }

        if (!isValidUsernameOrPassword(password)) {
            // ...throw InvalidPasswordException...
        }
    }

    private boolean isValidUsernameOrPassword(String usernameOrPassword) {
        //省略实现逻辑
        //跟原来的isValidUsername()或isValidPassword()的实现逻辑一样...
        return true;
    }
}