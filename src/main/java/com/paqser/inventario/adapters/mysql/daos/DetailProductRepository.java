package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface DetailProductRepository extends JpaRepository<DetailProductEntity, Long> {

    @Query("select new DetailProductEntity(c.idDetailProduct, c.stock, c.salePrice) " +
            "from DetailProductEntity c where c.idDetailProduct = :id")
    DetailProductEntity findByIdDetailProductSimple(@Param("id") Long idDetailProduct);

    DetailProductEntity findByIdDetailProduct(Long idDetailProduct);

    @Modifying
    @Query("update DetailProductEntity d set " +
            "d.stock = :stock " +
            "where d.idDetailProduct = :id")
    void updateStockById(@Param("id") Long idDetailProduct,
                         @Param("stock")BigDecimal stock);

}
