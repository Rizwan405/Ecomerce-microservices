package com.rizwan.ecomerce.Notification.kafka;

import com.rizwan.ecomerce.Notification.NotificationEntity;
import com.rizwan.ecomerce.Notification.NotificationRepository;
import com.rizwan.ecomerce.Notification.kafka.order.OrderConfirmation;
import com.rizwan.ecomerce.Notification.kafka.payment.PaymentConfirmation;
import com.rizwan.ecomerce.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.rizwan.ecomerce.Notification.NotificationType.ORDER_CONFIRMATION;
import static com.rizwan.ecomerce.Notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j

public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(
                NotificationEntity.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());
//        sending email
        var customerName = paymentConfirmation.customerFirstName() + paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail()
                ,customerName,
                paymentConfirmation.orderReference()
                ,paymentConfirmation.amount()
        );
    }
    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", orderConfirmation));
        repository.save(
                NotificationEntity.builder()
                        .notificationType(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build());
        var customerName = orderConfirmation.customer().firstName() + orderConfirmation.customer().lastName();
        emailService.sendOrderSuccessEmail(
                orderConfirmation.customer().email()
                ,customerName
                ,orderConfirmation.orderReference()
                ,orderConfirmation.totalAmount()
                ,orderConfirmation.products()
        );
    }
}
