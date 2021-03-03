package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.persistencePorts.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

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

    public Stream<Sale> listSales()
    {
        return this.salePersistence.listSales();
    }

    public Stream<Sale> listSalesByDate(Date ini, Date fin)
    {
        return this.salePersistence.listSalesByDate(ini, fin);
    }

}
