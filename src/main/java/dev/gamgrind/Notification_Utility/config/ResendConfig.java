package dev.gamgrind.Notification_Utility.config;

import com.resend.Resend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResendConfig {

    @Value("${resend.apiKey}")
    private String RESEND_API_KEY;

    @Bean
    Resend configResendConfig(){
        return new Resend(RESEND_API_KEY);
    }
}
