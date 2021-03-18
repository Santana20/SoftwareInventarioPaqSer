package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Purchase;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface PurchasePersistence {

    Purchase registerPurchase(Purchase purchase);

    Stream<Purchase> listSalesByDate(Date dateIni, Date dateFin);

    Stream<Purchase> listPurchases();
}
