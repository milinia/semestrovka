package ru.itis.services.old;

import models.Subscription;
import ru.itis.models.Subscription;
import services.old.EmailService;


public class EmailServiceImpl implements EmailService {

    private String login;
    private String password;

    public EmailServiceImpl(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void sendMessage(Subscription subscription, String email) {
//        UserServiceImpl userService = new UserServiceImpl();
//
//        String date = subscription.getEndingDate();
//        String serviceName = subscription.getServiceName();
//        String comment = subscription.getComment();
//
//        String to = email;
//        User user = userService.findByEmail(to);
//        String from = "memetovaevelina360@gmail.com";
//        String host = "smtp.gmail.com";
//        int port = 465;
//
//        Properties properties = new Properties();
//        properties.put("gmail.smtp.host", host);
//        properties.put("gmail.smtp.ssl.enable", "true");
//        properties.put("gmail.smtp.port", port);
//        properties.put("gmail.smtp.auth", "true");
//        properties.put("gmail.debug", "true");
//
//        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {
//            @Override
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new javax.mail.PasswordAuthentication(login, password);
//            }
//        });
//
//        try {
//            javax.mail.Message msg = new MimeMessage(session);
//
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject("Subscriptions in one place: notification");
//            msg.setSentDate(new Date(date));
//            String message = "Dear" + user.getName() + " " + user.getSurname() + ", "
//                    + "\n" + "Today, " + date.toString() + "your subscription on " + serviceName +"expires" + ". " +
//                    "So, please decide if you want to unsubscribe or to renew. " + "\n" + "Your comment: " + comment;
//
//            msg.setText(message);
//            Transport.send(msg);
//        }
//        catch (MessagingException mex) {
//            System.out.println("Failed to send email!");
//        }

    }
}
