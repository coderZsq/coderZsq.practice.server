package design_principles.dip.di.case3;

public class Demo {
    public static void main(String[] args) {
        //使用Notification
        MessageSender sender = new SmsSender(); //创建对象
        Notification notification = new Notification(sender);//依赖注入
        notification.sendMessage("13918942177", "短信验证码：2346");
    }
}
