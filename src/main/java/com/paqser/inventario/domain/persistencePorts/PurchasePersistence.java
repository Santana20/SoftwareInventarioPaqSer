package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Purchase;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePersistence {

    Purchase registerPurchase(Purchase purchase);

}
