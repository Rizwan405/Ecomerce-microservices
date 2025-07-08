package com.rizwan.order.OrderLine;

import com.rizwan.order.Order.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.id())
                .productId(orderLineRequest.productId())
                .quantity(orderLineRequest.quantity())
                .order(OrderEntity.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .build();
    }
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
