package dev.gamgrind.Notification_Utility.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    public final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String body ) throws MessagingException {
        logger.info("[Inside the Email Service] ");
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);


        helper.setText(body, true);

        mailSender.send(mimeMessage);
        logger.info("[Inside the Email Service] : Email Sent ");
    }

    // A helper method that holds your HTML structure as a String block

}
