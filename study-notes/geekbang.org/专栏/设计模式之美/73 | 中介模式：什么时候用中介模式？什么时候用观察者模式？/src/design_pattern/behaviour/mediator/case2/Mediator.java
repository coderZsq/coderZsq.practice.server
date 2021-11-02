package design_pattern.behaviour.mediator.case2;

import design_pattern.behaviour.mediator.case1.Button;
import design_pattern.behaviour.mediator.case1.Input;
import design_pattern.behaviour.mediator.case1.Selection;
import design_pattern.behaviour.mediator.case1.Text;

public interface Mediator {
    void handleEvent(Component component, String event);

    void setLoginButton(Button loginButton);

    void setRegButton(Button regButton);

    void setUsernameInput(Input usernameInput);

    void setPasswordInput(Input passwordInput);

    void setRepeatedPswdInput(Input repeatedPswdInput);

    void setHintText(Text hintText);

    void setSelection(Selection selection);
}
