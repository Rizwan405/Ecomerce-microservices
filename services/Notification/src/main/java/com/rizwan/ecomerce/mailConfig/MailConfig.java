package com.rizwan.ecomerce.mailConfig;

import org.apache.kafka.common.utils.Java;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender mailSender() {
        return new JavaMailSenderImpl();
    }
}
