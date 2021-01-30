package service;


import entity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {

    public void sendEmailToUSer(User user) {
        final Properties properties = new Properties();
        try {
            properties.load(SendEmail.class.getClassLoader().getResourceAsStream("mail.properties"));
            properties.put("mail.smtp.starttls.enable", "true");
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress("yor email"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("your password");
            message.setText("Hello your password is: " + user.getPassword());
            Transport tr = mailSession.getTransport();
            tr.connect("ypur name", "your password");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }


    }
}
