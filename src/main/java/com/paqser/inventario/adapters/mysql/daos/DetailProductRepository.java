package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface DetailProductRepository extends JpaRepository<DetailProductEntity, Long> {

    <T> T findByIdDetailProduct(Long idDetailProduct, Class<T> type);

    <T> List<T> findAllByProductEntity_IdProduct(String idProduct, Class<T> type);

    @Modifying
    @Query("update DetailProductEntity d set " +
            "d.stock = :stock " +
            "where d.idDetailProduct = :id")
    void updateStockById(@Param("id") Long idDetailProduct,
                         @Param("stock")BigDecimal stock);

}
