package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    <T> List<T> findAllByDateSaleBetween(Date ini, Date fin, Class<T> type);

    <T> List<T> findAllBy(Class<T> type);

    <T> T findByIdSale(Long idSale, Class<T> type);

    @Modifying
    @Query("update SaleEntity s set s.status = :status where s.idSale = :id")
    void updateStatusByIdSale(@Param("id") Long idSale,
                              @Param("status") boolean status);
}
