package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.ProductType;
import com.paqser.inventario.domain.persistencePorts.ProductTypePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProductTypeService {

    private ProductTypePersistence productTypePersistence;

    @Autowired
    public ProductTypeService(ProductTypePersistence productTypePersistence) {
        this.productTypePersistence = productTypePersistence;
    }

    public ProductType createProductType(ProductType productType) {
        return this.productTypePersistence.createProductType(productType);
    }

    public Stream<ProductType> listAllProductTypes() {
        return this.productTypePersistence.listAllProductTypes();
    }

}
