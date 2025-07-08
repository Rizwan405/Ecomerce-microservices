package com.rizwan.ecomerce.Notification;

import com.rizwan.ecomerce.Notification.kafka.payment.PaymentConfirmation;
import com.rizwan.ecomerce.Notification.kafka.order.OrderConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class NotificationEntity {
    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;


}
