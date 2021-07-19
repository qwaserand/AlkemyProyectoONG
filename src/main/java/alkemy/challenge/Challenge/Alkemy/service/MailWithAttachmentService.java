package alkemy.challenge.Challenge.Alkemy.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.IOException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@NoArgsConstructor
@AllArgsConstructor
public class MailWithAttachmentService {

    private String username = "";
    private String password = "";
    private String host = "";
    private String port = "";

    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.port", this.port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        return session;
    }

    public Message createMail(Session session) throws AddressException, MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("contacto@alkemy.org"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mail@gmail.com"));
        message.setSubject("Welcome Message");

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("This a welcome message");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(messageBodyPart, "src/main/resources/Templates/welcomeMail.html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        return message;
    }

    public void sendMail(Session session) throws MessagingException, IOException {

        Message message = createMail(session);
        Transport.send(message);
    }

}
