package com.sq.dp.designpattern.mediator;

public class Client {
    public static void main(String[] args) {
        IServer server = new Server();

        IUser ming = new User("小明");
        IUser san = new User("张三");

        server.register(ming);
        server.register(san);

        ming.send("张三", "张三你好啊! ");
        san.send("小明", "我挺好的! ");
    }
}
