package com.sq.mail.web.controller;

import com.sq.mail.bean.Mail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

@RestController
public class MailController {
    public static String myEmailAccount = "a13701777868@sina.com";
    public static String myEmailPassword = "a5ad9a321fb6d59c";
    public static String myEmailSMTPHost = "smtp.sina.com";

    public static MimeMessage createMimeMessage(Session session, Mail mail) throws Exception {
        System.out.println(mail.getSender());
        System.out.println(mail.getRecipient());
        System.out.println(mail.getSubject());
        System.out.println(mail.getBody());
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmailAccount, "coderZsq", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail.getRecipient(), mail.getRecipient(), "UTF-8"));
        message.setSubject("发件人: " + mail.getSender() + " / " + mail.getSubject(), "UTF-8");
        message.setContent(mail.getBody(), "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }

    @RequestMapping("/mailto")
    public void mailto(Mail mail) {
        sendMail(mail);
    }

    public void sendMail(Mail mail) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.smtp.host", myEmailSMTPHost);
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.port", "465");

            Session session = Session.getInstance(props);
            session.setDebug(true);
            MimeMessage message = createMimeMessage(session, mail);
            Transport transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testMail() {
        try (OutputStream out = new FileOutputStream("myEmail.eml")) {
            Properties props = new Properties();
            Session session = Session.getInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("a13701777868@sina.com", "coderZsq", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("cc@receive.com", "USER_CC", "UTF-8"));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));
            message.setSubject("邮件主题", "UTF-8");
            message.setContent("这是邮件正文", "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();
            message.writeTo(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
