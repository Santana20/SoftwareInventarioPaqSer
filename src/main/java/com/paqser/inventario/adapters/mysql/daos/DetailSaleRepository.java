package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailSaleRepository extends JpaRepository<DetailSaleEntity, Long> {

    <T> List<T> findAllBySaleEntity_IdSale(Long idSale, Class<T> type);

}
