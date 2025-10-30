package com.example.merchant_service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.merchant_service.entity.Merchant;
import com.example.merchant_service.repository.MerchantRepository;
import com.example.merchant_service.service.MerchantService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MerchantServiceImpl implements MerchantService{

    private final MerchantRepository merchantRepository;


    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }
    @Override
    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant getMerchantById(Long id) {
        return merchantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Merchant not found with id: " + id));
    }

    @Override
    public Merchant updateMerchant(Long id, Merchant updatedMerchant) {
        Merchant existingMerchant = getMerchantById(id);
        existingMerchant.setName(updatedMerchant.getName());
        existingMerchant.setEmail(updatedMerchant.getEmail());
        existingMerchant.setAddress(updatedMerchant.getAddress());
        // Update other fields as necessary
        return merchantRepository.save(existingMerchant);
    }

    @Override
    public void deleteMerchant(Long id) {
         if (!merchantRepository.existsById(id)) {
            throw new EntityNotFoundException("Merchant not found with id: " + id);
        }
        merchantRepository.deleteById(id);
    }
    @Override
    public boolean existsById(Long id) {
        return merchantRepository.existsById(id);    
    }
}
