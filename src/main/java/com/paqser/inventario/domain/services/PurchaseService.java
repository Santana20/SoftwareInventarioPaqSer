package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Purchase;
import com.paqser.inventario.domain.persistencePorts.PurchasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    private final PurchasePersistence purchasePersistence;

    @Autowired
    public PurchaseService(PurchasePersistence purchasePersistence) {
        this.purchasePersistence = purchasePersistence;
    }

    public Purchase registerPurchase(Purchase purchase) {
        return this.purchasePersistence.registerPurchase(purchase);
    }
}
