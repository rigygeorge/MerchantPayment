package com.example.payment_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.payment_service.entity.Payment.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
      private Long id;
    private String paymentReferenceId;
    private Long merchantId;
    private BigDecimal amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;
}
