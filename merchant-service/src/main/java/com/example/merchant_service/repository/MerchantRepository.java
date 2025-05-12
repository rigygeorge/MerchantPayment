package com.example.merchant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.merchant_service.entity.Merchant;


public interface MerchantRepository extends JpaRepository<Merchant, Long>{

}
