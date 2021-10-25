package design_principles.dip.di.case1;

// 非依赖注入实现方式
public class Notification {
    private MessageSender messageSender;

    public Notification() {
        this.messageSender = new MessageSender(); //此处有点像hardcode
    }

    public void sendMessage(String cellphone, String message) {
        //...省略校验逻辑等...
        this.messageSender.send(cellphone, message);
    }
}