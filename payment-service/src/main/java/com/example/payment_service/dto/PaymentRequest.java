package com.example.payment_service.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull
    private Long merchantId;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;
    
}
