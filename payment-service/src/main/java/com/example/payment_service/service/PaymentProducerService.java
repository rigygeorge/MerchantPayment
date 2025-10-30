package com.example.payment_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.payment_service.entity.PaymentEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentProducerService {

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public static final String TOPIC = "payment_events";

    public void sendPaymentEvent(PaymentEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("✅ Published PaymentEvent to Kafka topic: " + TOPIC);
        System.out.println(event);
    }

}
