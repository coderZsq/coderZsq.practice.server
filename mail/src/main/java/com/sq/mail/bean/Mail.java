package com.sq.mail.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mail {
    private String sender;
    private String subject;
    private String body;
    private String recipient;
}
