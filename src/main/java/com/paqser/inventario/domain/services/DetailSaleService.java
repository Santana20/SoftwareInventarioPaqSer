package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.persistencePorts.DetailSalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DetailSaleService {

    private final DetailSalePersistence detailSalePersistence;

    @Autowired
    public DetailSaleService(DetailSalePersistence detailSalePersistence) {
        this.detailSalePersistence = detailSalePersistence;
    }

    public Stream<DetailSale> findAllDetailSaleByIdSale(Long idSale)
    {
        return this.detailSalePersistence.findAllDetailSaleByIdSale(idSale);
    }

}
