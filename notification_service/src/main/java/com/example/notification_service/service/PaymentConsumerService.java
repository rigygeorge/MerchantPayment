package com.example.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.notification_service.model.PaymentEvent;

@Service
public class PaymentConsumerService {

        private static final Logger logger = LoggerFactory.getLogger(PaymentConsumerService.class);

    @KafkaListener(topics = "payment_events", groupId = "notification_group")
    public void consumePayment(PaymentEvent event) {

        logger.info("📩 Received PaymentEvent: {}", event);

        System.out.println("Notification Service: Received payment event -> " + event);
        // Simulate sending email/SMS
        System.out.println("Notification sent to merchant: " + event.getMerchantId());
    }
    
}
