package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    <T> T findById(Long id, Class<T> type);

    <T> List<T> findAllBy(Class<T> type);

    <T> List<T> findAllByProductType_IdProductType(Long idProductType, Class<T> type);

    <T> List<T> findAllByBrand_IdBrand(Long idBrand, Class<T> type);

    <T> List<T> findAllByBrand_IdBrandAndProductType_IdProductType(Long idBrand, Long idProductType, Class<T> type);

    <T> List<T> findAllByNameProductContains(String nameProduct, Class<T> type);

    <T> List<T> findAllByNameProductContainsAndProductType_IdProductType(String nameProduct, Long idProductType, Class<T> type);

    <T> List<T> findAllByNameProductContainsAndBrand_IdBrand(String nameProduct, Long idBrand, Class<T> type);

    <T> List<T> findAllByNameProductContainsAndBrand_IdBrandAndProductType_IdProductType(String nameProduct, Long idBrand, Long idProductType, Class<T> type);

    boolean existsByCodProductAndBrand_IdBrandAndProductType_IdProductTypeAndNameProduct(String codProduct, Long idBrand, Long idProductType, String nameProduct);

}
