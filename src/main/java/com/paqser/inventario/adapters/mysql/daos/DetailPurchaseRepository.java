package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailPurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailPurchaseRepository extends JpaRepository<DetailPurchaseEntity, Long> {

    <T> List<T> findAllByPurchaseEntity_IdPurchase(Long idPurchase, Class<T> type);

}
