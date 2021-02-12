package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Sale;
import org.springframework.stereotype.Repository;

@Repository
public interface SalePersistence {

    Sale registerSale(Sale sale);

}
