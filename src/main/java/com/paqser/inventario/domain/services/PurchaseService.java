package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Purchase;
import com.paqser.inventario.domain.persistencePorts.PurchasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

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

    public Stream<Purchase> listSalesByDate(Date dateIni, Date dateFin) {
        return this.purchasePersistence.listSalesByDate(dateIni, dateFin);
    }

    public Stream<Purchase> listPurchases() {
        return this.purchasePersistence.listPurchases();
    }
}
