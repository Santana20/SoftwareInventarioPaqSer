package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailSaleRepository extends JpaRepository<DetailSaleEntity, Long> {

}
