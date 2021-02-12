package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {



}
