package com.sq.mail.web.controller;

import com.sq.mail.bean.Mail;
import com.sq.mail.service.MailService;
import com.sq.mail.service.impl.MailServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    private MailService service = new MailServiceImpl();

    @RequestMapping("/mailto")
    public void mailto(Mail mail) {
        service.sendMail(mail);
    }
}
