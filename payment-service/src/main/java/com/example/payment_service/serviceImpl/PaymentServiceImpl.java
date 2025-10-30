package com.example.payment_service.serviceImpl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.payment_service.dto.PaymentDTO;
import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.entity.PaymentEvent;
import com.example.payment_service.exception.ResourceNotFoundException;
import com.example.payment_service.repository.PaymentRepository;
import com.example.payment_service.service.PaymentProducerService;
import com.example.payment_service.service.PaymentService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;
    private final PaymentProducerService paymentProducerService;

    private static final String MERCHANT_SERVICE_URL = "http://localhost:8080/api/merchants";

    

    @Override
    public PaymentDTO createPayment(PaymentRequest paymentRequest, String authHeader) {

        // --- Call MerchantService to validate merchant ---
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader); // user JWT
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                MERCHANT_SERVICE_URL + "/exists/" + paymentRequest.getMerchantId(),
                HttpMethod.GET,
                entity,
                Boolean.class
        );

        Boolean merchantExists = response.getBody();
        if (merchantExists == null || !merchantExists) {
            throw new ResourceNotFoundException("Merchant not found with id " + paymentRequest.getMerchantId());
        }
        Payment payment = Payment.builder()
                .merchantId(paymentRequest.getMerchantId())
                .amount(paymentRequest.getAmount())
                .status(Payment.PaymentStatus.PENDING)
                .build();
        Payment saved = paymentRepository.save(payment);


        // Publish to Kafka
        PaymentEvent event = new PaymentEvent(saved.getId(),saved.getMerchantId(), saved.getAmount(), saved.getStatus().name());
        paymentProducerService.sendPaymentEvent(event);

        return mapToDTO(saved);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));
        return mapToDTO(payment);
    }

    @Override
    public List<PaymentDTO> getPaymentsByMerchant(Long merchantId){
        return paymentRepository.findByMerchantId(merchantId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> getAllPayments(){
        return paymentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public PaymentDTO updatePaymentStatus(Long id, Payment.PaymentStatus status) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Payment not found with id " + id));
        payment.setStatus(status);
        Payment updated = paymentRepository.save(payment);
        return mapToDTO(updated);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found with id " + id);
        }
        paymentRepository.deleteById(id);
    }

    // --- Mapper ---
    private PaymentDTO mapToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getPaymentReferenceId(),
                payment.getMerchantId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getCreatedAt()
        );
    }

}
