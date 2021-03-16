package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.DetailPurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailPurchaseRepository extends JpaRepository<DetailPurchaseEntity, Long> {
}
