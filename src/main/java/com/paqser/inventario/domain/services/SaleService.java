package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.persistencePorts.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Stream;

@Service
public class SaleService {

    private final SalePersistence salePersistence;

    @Autowired
    public SaleService(SalePersistence salePersistence) {
        this.salePersistence = salePersistence;
    }

    public Sale registerSale(Sale sale) {
        return this.salePersistence.registerSale(sale);
    }

    public Sale findSaleByIdSale(Long idSale) {
        return this.salePersistence.findSaleByIdSale(idSale);
    }

    public Stream<Sale> listSales(boolean isPDF)
    {
        return this.salePersistence.listSales(isPDF);
    }

    public Stream<Sale> listSalesByDate(Date ini, Date fin, boolean isPDF)
    {
        return this.salePersistence.listSalesByDate(ini, fin, isPDF);
    }

}
