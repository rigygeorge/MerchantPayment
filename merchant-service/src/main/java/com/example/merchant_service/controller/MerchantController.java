package com.example.merchant_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.merchant_service.entity.Merchant;
import com.example.merchant_service.service.MerchantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/merchants")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public Merchant createMerchant(@Valid @RequestBody Merchant merchant) {
        return merchantService.createMerchant(merchant);
    }

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public Merchant getMerchantById(@PathVariable Long id) {
        return merchantService.getMerchantById(id);
    }

    @PutMapping("/{id}")
    public Merchant updateMerchant(@PathVariable Long id, @Valid @RequestBody Merchant updatedMerchant) {
        return merchantService.updateMerchant(id, updatedMerchant);
    }

    @DeleteMapping("/{id}")
    public void deleteMerchant(@PathVariable Long id) {
        merchantService.deleteMerchant(id);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> merchantExists(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.existsById(id));
    }

}
