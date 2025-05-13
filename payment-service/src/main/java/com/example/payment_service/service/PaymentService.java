package com.example.payment_service.service;

import java.util.List;

import com.example.payment_service.entity.Payment;

public interface PaymentService {

    Payment createPayment(Payment payment);
    List<Payment> getAllPayments();
    Payment getPaymentById(Long id);
    Payment updatePayment(Long id, Payment payment);
    void deletePayment(Long id);
}
