package com.solartis.test.util.common;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail
{

    public static void main(String[]args) throws IOException {

        final String username = "sasirekha_a@solartis.com";
        final String password = "sasiOutlook2018";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sasirekha_a@solartis.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse("sasirekha_a@solartis.com"));
            message.setSubject("TestReport");
            message.setText("TestReport");

            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText("This is message body");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = "R:\\RestFullAPIDeliverable\\Devolpement\\admin\\STARR-DTC\\RatingServiceSinglePlan\\Reports\\Test73066_UAT_2018-03-08 18-07-35.zip";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Done");

        }
        catch (MessagingException e) 
        {
            throw new RuntimeException(e);
        }
    }
}
