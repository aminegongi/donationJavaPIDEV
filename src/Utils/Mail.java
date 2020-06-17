/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.UserTest;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hedi
 */
public class Mail {
    
        public static void sendMail(String titrePart) {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "donation.pidev@gmail.com";
        //Your gmail password
        String password = "Donation2020";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        //Message message = prepareMessage(session, myAccountEmail, user.getMail(), user);
        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(UserTest.emailSendTo));
        message.setSubject("Donation , Votre Compte");
       System.out.println("before  html"); 
        //String htmlCode = "<h1> Merci </h1> <br/> <h2><b>votre code confirmation :" + user.getConfirmation_token() + "</b></h2>";
        //String htmlCode = "<h1> Bonne nouvelle! quelqu'un peut vous aider :) </h1> <br/> <p>l'utilisateur :" + UserTest.emailSigned + " a participé a votre demande: <b>"+titrePart+" </b>N'hésitez-pas à le contacter pour obtenir plus d'informations!</p>";
       String htmlCode ="hi";
System.out.println("Mail after html");
            message.setContent(htmlCode, "text/html");
            //Send mail
            System.out.println("Mail before transport ");
            Transport.send(message);
            System.out.println("Mail Confirm key OK");

        } catch (Exception ex) {
            System.out.println("Mail Confirm key Problem");
        }
    }
}
