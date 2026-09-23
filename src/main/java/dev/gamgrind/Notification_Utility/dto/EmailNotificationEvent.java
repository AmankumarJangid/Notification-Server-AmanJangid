package dev.gamgrind.Notification_Utility.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmailNotificationEvent {
    private String email;
    private String subject;
    private String message;
}
