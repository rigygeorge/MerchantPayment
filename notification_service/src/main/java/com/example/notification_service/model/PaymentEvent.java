package com.example.notification_service.model;

import java.math.BigDecimal;

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
