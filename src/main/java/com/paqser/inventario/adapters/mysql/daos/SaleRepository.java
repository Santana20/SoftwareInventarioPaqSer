package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findAllByDateSaleBetween(Date ini, Date fin);

}
