package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {

    <T> List<T> findAllByDatePurchaseBetween(Date ini, Date fin, Class<T> type);

    <T> List<T> findAllBy(Class<T> type);

}
