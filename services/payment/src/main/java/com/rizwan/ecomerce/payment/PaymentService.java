package com.rizwan.ecomerce.payment;

import com.rizwan.ecomerce.notification.NotificationProducer;
import com.rizwan.ecomerce.notification.PaymentNotification;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentMapper mapper;
    private final PaymentRepository repository;
    private final NotificationProducer producer;
    public Integer createPayment(@Valid paymentRequest request) {
        var payment =repository.save(mapper.toPaymentEntity(request));
//        send notification
        producer.sendNotification(
                new PaymentNotification(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
