package com.example.payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.entity.PaymentEvent;
import com.example.payment_service.service.PaymentProducerService;
import com.example.payment_service.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody @Valid PaymentRequest request,
            @RequestHeader("Authorization") String authHeader) {
        return new ResponseEntity<>(paymentService.createPayment(request, authHeader), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsForMerchant(@PathVariable Long merchantId) {
        return ResponseEntity.ok(paymentService.getPaymentsByMerchant(merchantId));
    }

    
}
