package design_pattern.behaviour.mediator.case2;

import design_pattern.behaviour.mediator.case1.*;

public class UIControl {
    private static final String LOGIN_BTN_ID = "login_btn";
    private static final String REG_BTN_ID = "reg_btn";
    private static final String USERNAME_INPUT_ID = "username_input";
    private static final String PASSWORD_INPUT_ID = "pswd_input";
    private static final String REPEATED_PASSWORD_INPUT_ID = "repeated_pswd_input";
    private static final String HINT_TEXT_ID = "hint_text";
    private static final String SELECTION_ID = "selection";

    public static void main(String[] args) {
        Button loginButton = (Button) findViewById(LOGIN_BTN_ID);
        Button regButton = (Button) findViewById(REG_BTN_ID);
        Input usernameInput = (Input) findViewById(USERNAME_INPUT_ID);
        Input passwordInput = (Input) findViewById(PASSWORD_INPUT_ID);
        Input repeatedPswdInput = (Input) findViewById(REPEATED_PASSWORD_INPUT_ID);
        Text hintText = (Text) findViewById(HINT_TEXT_ID);
        Selection selection = (Selection) findViewById(SELECTION_ID);

        Mediator dialog = new LandingPageDialog();
        dialog.setLoginButton(loginButton);
        dialog.setRegButton(regButton);
        dialog.setUsernameInput(usernameInput);
        dialog.setPasswordInput(passwordInput);
        dialog.setRepeatedPswdInput(repeatedPswdInput);
        dialog.setHintText(hintText);
        dialog.setSelection(selection);

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.handleEvent(loginButton, "click");
            }
        });

        regButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.handleEvent(regButton, "click");
            }
        });

        //....
    }

    private static Object findViewById(String loginBtnId) {
        return null;
    }
}