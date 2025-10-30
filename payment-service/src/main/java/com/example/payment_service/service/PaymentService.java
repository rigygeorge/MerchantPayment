package com.example.payment_service.service;

import java.util.List;

import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.entity.Payment;
    
public interface PaymentService {

    PaymentDTO createPayment(PaymentRequest paymentRequest, String authHeader);
    PaymentDTO getPaymentById(Long id);
    List<PaymentDTO> getPaymentsByMerchant(Long merchantId);
    List<PaymentDTO> getAllPayments();
    PaymentDTO updatePaymentStatus(Long id, Payment.PaymentStatus status);
    void deletePayment(Long id);
}
