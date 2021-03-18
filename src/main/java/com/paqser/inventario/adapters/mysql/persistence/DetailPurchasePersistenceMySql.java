package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.DetailPurchaseRepository;
import com.paqser.inventario.adapters.mysql.projections.DetailPurchaseWithoutPurchase;
import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.persistencePorts.DetailPurchasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("DetailPurchasePersistence")
public class DetailPurchasePersistenceMySql implements DetailPurchasePersistence {

    private final DetailPurchaseRepository detailPurchaseRepository;

    @Autowired
    public DetailPurchasePersistenceMySql(DetailPurchaseRepository detailPurchaseRepository) {
        this.detailPurchaseRepository = detailPurchaseRepository;
    }

    @Override
    public Stream<DetailPurchase> findAllDetailPurchaseByIdPurchase(Long idPurchase) {
        return this.detailPurchaseRepository.findAllByPurchaseEntity_IdPurchase(idPurchase, DetailPurchaseWithoutPurchase.class)
                .stream().map(DetailPurchaseWithoutPurchase::toDetailPurchase);
    }
}
