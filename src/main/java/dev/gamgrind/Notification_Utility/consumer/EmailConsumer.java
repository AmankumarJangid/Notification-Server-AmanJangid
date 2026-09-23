package dev.gamgrind.Notification_Utility.consumer;

import dev.gamgrind.Notification_Utility.dto.EmailNotificationEvent;
import dev.gamgrind.Notification_Utility.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@AllArgsConstructor
public class EmailConsumer {

    private EmailService emailService;

    @KafkaListener(topics = "email-notification" , groupId = "notification-group")
    public void postEmail(EmailNotificationEvent message){

        try{
        emailService.sendMail(message.getEmail() , message.getSubject(), message.getMessage());

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
