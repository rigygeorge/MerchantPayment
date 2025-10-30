package com.example.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentEvent {

    private Long paymentId;
    private Long merchantId;
    private BigDecimal amount; // must match Payment.amount
    private String status; // e.g., "PENDING", "SUCCESS"
    
}
