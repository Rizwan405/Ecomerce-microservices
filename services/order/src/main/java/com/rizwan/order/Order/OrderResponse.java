package com.rizwan.order.Order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String refernce,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
