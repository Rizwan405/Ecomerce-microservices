package com.rizwan.order.Order;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderEntity toOrder(@Valid OrderRequest request) {
        return OrderEntity.builder().
                id(request.id())
                .reference(request.reference())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(OrderEntity orderEntity) {
        return new OrderResponse(
                orderEntity.getId(),
                orderEntity.getReference(),
                orderEntity.getTotalAmount(),
                orderEntity.getPaymentMethod(),
                orderEntity.getCustomerId());
    }
}
