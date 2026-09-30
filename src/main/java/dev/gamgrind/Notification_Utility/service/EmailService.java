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

    @Value("${resend.domain}")
    private String domain;

    public final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body)
            throws Exception
    {

        logger.info("[Inside the Email Service] ");

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(String.format("Aman <mail@%s>", domain))
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

}
