package com.rizwan.order.kafka;

import com.rizwan.order.Customer.CustomerResponse;
import com.rizwan.order.Order.PaymentMethod;
import com.rizwan.order.Product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
