package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.persistencePorts.DetailPurchasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DetailPurchaseService {

    private final DetailPurchasePersistence detailPurchasePersistence;

    @Autowired
    public DetailPurchaseService(DetailPurchasePersistence detailPurchasePersistence) {
        this.detailPurchasePersistence = detailPurchasePersistence;
    }

    public Stream<DetailPurchase> findAllDetailPurchaseByIdPurchase(Long idPurchase)
    {
        return this.detailPurchasePersistence.findAllDetailPurchaseByIdPurchase(idPurchase);
    }

}
