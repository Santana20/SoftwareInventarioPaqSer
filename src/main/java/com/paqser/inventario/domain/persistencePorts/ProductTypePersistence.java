package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.ProductType;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductTypePersistence {

    ProductType createProductType(ProductType productType);

    Stream<ProductType> listAllProductTypes();

}
