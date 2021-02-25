package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Sale;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface SalePersistence {

    Sale registerSale(Sale sale);

    Stream<Sale> listSales();

    Stream<Sale> listSalesByDate(Date ini, Date fin);

}
