package dev.gamgrind.Notification_Utility.controller;

import dev.gamgrind.Notification_Utility.dto.EmailNotificationEvent;
import dev.gamgrind.Notification_Utility.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification/v1")
@AllArgsConstructor
public class EmailController {


    private final EmailService emailService;

    @GetMapping("/health")
    private ResponseEntity<String> healthCheck(){return ResponseEntity.ok("Status : OK");}


    @PostMapping("/email")
    private ResponseEntity<EmailNotificationEvent> postEmail(@RequestBody EmailNotificationEvent message){
        try{
            emailService.sendMail(message.getEmail() , message.getSubject(), message.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

}
