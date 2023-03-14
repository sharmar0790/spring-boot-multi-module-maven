package com.example;

import com.example.entity.Purchases;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void savePurchase(Purchases purchases) {
        this.purchaseRepository.save(purchases);
    }
}
