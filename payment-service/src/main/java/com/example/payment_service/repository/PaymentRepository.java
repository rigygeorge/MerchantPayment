package com.example.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.payment_service.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

    List<Payment> findByMerchantId(Long merchantId);

    List<Payment> findByStatus(Payment.PaymentStatus status);

}
