package com.sq.mail.service.impl;

import com.sq.mail.bean.Mail;
import com.sq.mail.service.MailService;
import com.sq.mail.util.Mails;

public class MailServiceImpl implements MailService {
    @Override
    public void sendMail(Mail mail) {
        Mails.send(mail);
    }
}
