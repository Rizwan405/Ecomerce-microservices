package com.rizwan.ecomerce.notification;

import com.rizwan.ecomerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotification(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
