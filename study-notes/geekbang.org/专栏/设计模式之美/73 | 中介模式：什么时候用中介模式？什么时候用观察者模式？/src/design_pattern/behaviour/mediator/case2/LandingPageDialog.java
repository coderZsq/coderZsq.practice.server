package design_pattern.behaviour.mediator.case2;

import design_pattern.behaviour.mediator.case1.Button;
import design_pattern.behaviour.mediator.case1.Input;
import design_pattern.behaviour.mediator.case1.Selection;
import design_pattern.behaviour.mediator.case1.Text;

public class LandingPageDialog implements Mediator {
    private Button loginButton;
    private Button regButton;
    private Selection selection;
    private Input usernameInput;
    private Input passwordInput;
    private Input repeatedPswdInput;
    private Text hintText;

    @Override
    public void handleEvent(Component component, String event) {
        if (component.equals(loginButton)) {
            String username = usernameInput.text();
            String password = passwordInput.text();
            //校验数据...
            //做业务处理...
        } else if (component.equals(regButton)) {
            //获取usernameInput、passwordInput、repeatedPswdInput数据...
            //校验数据...
            //做业务处理...
        } else if (component.equals(selection)) {
            String selectedItem = selection.select();
            if (selectedItem.equals("login")) {
                usernameInput.show();
                passwordInput.show();
                repeatedPswdInput.hide();
                hintText.hide();
                //...省略其他代码
            } else if (selectedItem.equals("register")) {
                //....
            }
        }
    }

    @Override
    public void setLoginButton(Button loginButton) {
        this.loginButton = loginButton;
    }

    @Override
    public void setRegButton(Button regButton) {
        this.regButton = regButton;
    }

    @Override
    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    @Override
    public void setUsernameInput(Input usernameInput) {
        this.usernameInput = usernameInput;
    }

    @Override
    public void setPasswordInput(Input passwordInput) {
        this.passwordInput = passwordInput;
    }

    @Override
    public void setRepeatedPswdInput(Input repeatedPswdInput) {
        this.repeatedPswdInput = repeatedPswdInput;
    }

    @Override
    public void setHintText(Text hintText) {
        this.hintText = hintText;
    }
}
