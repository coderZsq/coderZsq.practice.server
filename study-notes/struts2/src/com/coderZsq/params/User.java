package com.coderZsq.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class User {
    private String username;
    private String password;

    public User() {
        System.out.println("User.User()");
    }
}
