package com.rizwan.ecomerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor

public class NotificationProducer {
//    kafka template
    private final KafkaTemplate<String, PaymentNotification> kafkaTemplate;
//    now sending message with kafka
    public void sendNotification(PaymentNotification request){
        log.info("Sending message with body :<{}>", request);
        Message<PaymentNotification> message = MessageBuilder.withPayload(request)
                .setHeader(TOPIC, "payment")
                .build();
        kafkaTemplate.send(message);


    }
}
