package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.projections.DetailSaleWithoutSale;
import com.paqser.inventario.adapters.mysql.daos.DetailSaleRepository;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.persistencePorts.DetailSalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("DetailSalePersistence")
public class DetailSalePersistenceMySql implements DetailSalePersistence {

    private final DetailSaleRepository detailSaleRepository;

    @Autowired
    public DetailSalePersistenceMySql(DetailSaleRepository detailSaleRepository) {
        this.detailSaleRepository = detailSaleRepository;
    }

    @Override
    public Stream<DetailSale> findAllDetailSaleByIdSale(Long idSale) {
        return this.detailSaleRepository.findAllBySaleEntity_IdSale(idSale, DetailSaleWithoutSale.class)
                .stream().map(DetailSaleWithoutSale::toDetailSale);
    }
}
