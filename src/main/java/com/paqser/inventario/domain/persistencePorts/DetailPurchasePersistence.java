package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.DetailPurchase;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DetailPurchasePersistence {

    Stream<DetailPurchase> findAllDetailPurchaseByIdPurchase(Long idPurchase);
}
