package alkemy.challenge.Challenge.Alkemy.controller;

import alkemy.challenge.Challenge.Alkemy.model.EmailRequest;
import alkemy.challenge.Challenge.Alkemy.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@ApiIgnore
@RestController
@RequestMapping("/sendEmail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmailTo")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailrequest) {
        Response response = emailService.sendEmail(emailrequest);
        if (response.getStatusCode() == 200 || response.getStatusCode() == 202)
            return new ResponseEntity<>("Mail enviado!", HttpStatus.OK);
        return new ResponseEntity<>("Fallo el envio!", HttpStatus.NOT_FOUND);
    }

    //se crea metodo para enviar mail a contacto
    @PostMapping("/contactMail")
    public String sendContactMail(@RequestBody EmailRequest emailRequest) {
        Email from = new Email("yourname@yourhostname.de");
        String subject = "Thanks for Contacting Us";
        Email to = new Email("yourname@yourhostname.de");
        Content content = new Content("text/html", "Dear User, your registration form has been completed successfully. We really apreciate your contact. ");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("${sendgrid.key}");

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("/contactMail");
            request.setBody(mail.build());

            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return "email was successfully send";
    }
}



