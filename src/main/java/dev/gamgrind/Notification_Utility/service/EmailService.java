package dev.gamgrind.Notification_Utility.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    public final Logger logger = LoggerFactory.getLogger(EmailService.class);
    public final Resend resend;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body)
            throws Exception
    {

        logger.info("[Inside the Email Service] ");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Aman <mail@amanjangid.me>")
                .to(to)
                .subject(subject)
                .html(body)
                .build();
        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//
//            mailSender.send(mimeMessage);

            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
            logger.info("[Inside the Email Service] : Email Sent ");
//        } catch (MessagingException e) {
//            logger.error("[Inside the Email Service] : Error occurred while sending email", e);
//            throw e; // Rethrows the exception to the caller
        }
        catch ( ResendException e ){
            logger.error("[Inside the Email Service] : Resend Exception : {}", e.getMessage() );
            throw e;
        }
    }

//    void sendEmail(){
//        Resend resend = new Resend("re_dmfcLCiq_K8kiWZgGNGW8rBjMp6op9Eyo");
//
//        CreateEmailOptions params = CreateEmailOptions.builder()
//                .from("onboarding@resend.dev")
//                .to("amanjangid7847@gmail.com")
//                .subject("Hello World")
//                .html("<p>Congrats on sending your <strong>first email</strong>!</p>")
//                .build();
//
//        CreateEmailResponse data = resend.emails().send(params);
//    }


    // A helper method that holds your HTML structure as a String block

}
