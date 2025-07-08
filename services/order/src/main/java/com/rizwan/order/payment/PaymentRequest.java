package com.rizwan.order.payment;

import com.rizwan.order.Customer.CustomerResponse;
import com.rizwan.order.Order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer

) {
}
