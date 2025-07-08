package com.rizwan.ecomerce.payment;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public PaymentEntity toPaymentEntity(@Valid paymentRequest request) {
        return PaymentEntity.builder()
                .id(request.id())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())

                .build();
    }
}
