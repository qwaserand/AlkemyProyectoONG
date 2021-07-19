package alkemy.challenge.Challenge.Alkemy.service;

import alkemy.challenge.Challenge.Alkemy.model.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    SendGrid sendGrid;

    public Response sendEmail(EmailRequest emailrequest) {

        Mail mail = new Mail(new Email("email_usuario_grid@mail.com"),
                emailrequest.getSubject(),
                new Email(emailrequest.getTo()),
                new Content("text/plain", emailrequest.getBody()));
        mail.setReplyTo(new Email("a_que_mail_responder@mail.com"));
        Request request = new Request();

        Response response = null;

        try {

            request.setMethod(Method.POST);

            request.setEndpoint("mail/send");

            request.setBody(mail.build());

            response = this.sendGrid.api(request);

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        return response;
    }

}
