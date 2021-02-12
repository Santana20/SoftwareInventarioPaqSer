package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.ProductTypeRepository;
import com.paqser.inventario.adapters.mysql.entities.ProductTypeEntity;
import com.paqser.inventario.domain.models.ProductType;
import com.paqser.inventario.domain.persistencePorts.ProductTypePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ProductTypePersistence")
public class ProductTypePersistenceMySql implements ProductTypePersistence {

    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypePersistenceMySql(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public boolean existsNameProductType(String nameProductType) {
        return this.productTypeRepository.existsByNameProductType(nameProductType);
    }

    @Override
    public ProductType createProductType(ProductType productType) {
        if (this.existsNameProductType(productType.getNameProductType()))
            throw new RuntimeException("Ya se registro un tipo de producto con el nombre " + productType.getNameProductType()
            + ". Intentelo nuevamente.");

        return this.productTypeRepository
                .save(new ProductTypeEntity(productType))
                .toProductType();
    }

    @Override
    public Stream<ProductType> listAllProductTypes() {
        return this.productTypeRepository.findAll().stream()
                .map(productTypeEntity -> productTypeEntity.toProductType());
    }
}
