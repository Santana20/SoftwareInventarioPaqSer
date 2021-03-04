package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    <T> List<T> findAllByDateSaleBetween(Date ini, Date fin, Class<T> type);

    <T> List<T> findAllBy(Class<T> type);

}
