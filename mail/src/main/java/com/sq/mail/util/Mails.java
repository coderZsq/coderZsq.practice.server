package com.sq.mail.util;

import com.sq.mail.bean.Mail;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

public class Mails {
    public static void send(Mail mail) {
        String sender = mail.getSender();
        String recipient = mail.getRecipient();
        String subject = mail.getSubject();
        String body = mail.getBody();

        System.out.println("发件人: " + sender);
        System.out.println("收件人: " + recipient);
        System.out.println("主题: " + subject);
        System.out.println("内容: " + body);

        try (InputStream in = Mails.class.getClassLoader().getResourceAsStream("mail.properties");
             OutputStream out = new FileOutputStream("test.eml")) {
            Properties props = new Properties();
            props.load(in);

            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String personal = props.getProperty("personal");
            String charset = props.getProperty("charset");

            Session session = Session.getInstance(props);
            session.setDebug(true);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user, personal, charset));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient, recipient, charset));
            message.setSubject("发件人: " + sender + " / " + subject, charset);
            message.setContent(body, "text/html;charset=" + charset);
            message.setSentDate(new Date());
            message.saveChanges();
            message.writeTo(out);
            out.flush();

            Transport transport = session.getTransport();
            transport.connect(user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
