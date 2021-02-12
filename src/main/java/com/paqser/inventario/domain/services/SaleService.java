package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.persistencePorts.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    private SalePersistence salePersistence;

    @Autowired
    public SaleService(SalePersistence salePersistence) {
        this.salePersistence = salePersistence;
    }

    public Sale registerSale(Sale sale) {
        return this.salePersistence.registerSale(sale);
    }

}
