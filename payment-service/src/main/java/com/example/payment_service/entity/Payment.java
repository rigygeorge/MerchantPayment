package com.example.payment_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String paymentReferenceId =UUID.randomUUID().toString(); // unique ID (UUID)
    
    @NotNull(message = "Merchant ID is required")
    private Long merchantId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status; // e.g., PENDING, SUCCESS, FAILED

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        if (paymentReferenceId == null) {
            paymentReferenceId = UUID.randomUUID().toString();
        }
    }

    public enum PaymentStatus {
        PENDING, SUCCESS, FAILED
    }
}
