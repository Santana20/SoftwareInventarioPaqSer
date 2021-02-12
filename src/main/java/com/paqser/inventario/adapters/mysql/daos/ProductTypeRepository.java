package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.ProductTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {

    /**
     * @param nameProductType
     * @return if exists product type with "nameProductType" parameter, return true. Else, return false
     */
    boolean existsByNameProductType(String nameProductType);

}
