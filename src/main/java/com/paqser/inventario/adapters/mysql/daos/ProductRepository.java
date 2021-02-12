package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findByIdProduct(String idProduct);

    List<ProductEntity> findAllByProductType_IdProductType(Long idProductType);

    List<ProductEntity> findAllByBrand_IdBrand(Long idBrand);

    List<ProductEntity> findAllByBrand_IdBrandAndProductType_IdProductType(Long idBrand, Long idProductType);

    List<ProductEntity> findAllByNameProductContains(String nameProduct);

    List<ProductEntity> findAllByNameProductContainsAndProductType_IdProductType(String nameProduct, Long idProductType);

    List<ProductEntity> findAllByNameProductContainsAndBrand_IdBrand(String nameProduct, Long idBrand);

    List<ProductEntity> findAllByNameProductContainsAndBrand_IdBrandAndProductType_IdProductType(String nameProduct, Long idBrand, Long idProductType);

}
