package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.DetailSale;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DetailSalePersistence {
    Stream<DetailSale> findAllDetailSaleByIdSale(Long idSale);
}
