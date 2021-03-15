package com.sq.dp.designpattern.strategy;

public class Client {
    public static void main(String[] args) {
        UserController controller = new UserController();
        controller.handleData("email@e.com", new EmailValid());

        controller.handleData(null, new NullValid());

        controller.handleData("https://www.github.com/coderzsq", new UrlValid());
    }
}
