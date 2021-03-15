package com.sq.dp.designpattern.prototype;

public class ProtoTypeTest {
    public static void main(String[] args) {
        User user = new User("小明");
        User user1 = (User)user.clone();
        System.out.println(user);
        System.out.println(user1);
    }
}
