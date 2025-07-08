package com.rizwan.ecomerce.email;

import com.rizwan.ecomerce.Notification.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizwan.ecomerce.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.rizwan.ecomerce.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            String orderReference,
            BigDecimal orderAmount
    ) throws MessagingException {
        MimeMessage message =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        helper.setFrom("zaheer.1969876@studenti.uniroma1.it");
        final String templateName = PAYMENT_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("CustomerName", customerName);
        variables.put("amount", orderAmount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(PAYMENT_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destinationEmail);
            mailSender.send(message);
            log.info("Email sent to {}",destinationEmail);


        }catch (MessagingException e){
            log.warn("Error sending email to {}",destinationEmail,e);
        }


    }

    public void sendOrderSuccessEmail(
            String destinationEmail,
            String customerName,
            String orderReference,
            BigDecimal orderAmount,
            List<Product> products
    ) throws MessagingException {
        MimeMessage message =  mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_RELATED, UTF_8.name());
        helper.setFrom("zaheer.1969876@studenti.uniroma1.it");
        final String templateName = ORDER_CONFIRMATION.getTemplateName();
        Map<String, Object> variables = new HashMap<>();
        variables.put("CustomerName", customerName);
        variables.put("amount", orderAmount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);
        Context context = new Context();
        context.setVariables(variables);
        helper.setSubject(ORDER_CONFIRMATION.getSubject());
        try{
            String htmlTemplate = templateEngine.process(templateName,context);
            helper.setText(htmlTemplate, true);
            helper.setTo(destinationEmail);
            mailSender.send(message);
            log.info("Email sent to {}",destinationEmail);


        }catch (MessagingException e){
            log.warn("Error sending email to {}",destinationEmail,e);
        }


    }
}
