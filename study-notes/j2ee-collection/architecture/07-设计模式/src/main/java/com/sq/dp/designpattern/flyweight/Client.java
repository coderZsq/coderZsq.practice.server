package com.sq.dp.designpattern.flyweight;

public class Client {
    public static PanServer server = new PanServer();

    public static void main(String[] args) {
        User root = new User("001", "root");
        // 客户端与服务端建立连接
        PanClient client = new PanClient("C001", server);
        // 客户登录
        client.login(root);

        // 客户上传资源
        LocalFile localFile = new LocalFile("设计模式.pdf", "16Mb");
        client.upload(localFile);
        System.out.println();
        System.out.println("---------------------- 分隔符 ----------------------");
        System.out.println();
        User admin = new User("002", "admin");
        client.login(admin);
        client.upload(localFile);
        LocalFile localFile1 = new LocalFile("设计模式-1.pdf", "16.332Mb");
        client.upload(localFile1);
    }
}
