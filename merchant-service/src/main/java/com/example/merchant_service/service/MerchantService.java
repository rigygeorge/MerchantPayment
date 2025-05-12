package com.example.merchant_service.service;

import java.util.List;


import com.example.merchant_service.entity.Merchant;


public interface MerchantService {

    Merchant createMerchant(Merchant merchant);
    List<Merchant> getAllMerchants();
    Merchant getMerchantById(Long id);
    Merchant updateMerchant(Long id, Merchant updatedMerchant);
    void deleteMerchant(Long id);
}
