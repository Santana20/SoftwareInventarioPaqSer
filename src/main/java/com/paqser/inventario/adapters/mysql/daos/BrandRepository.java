package com.paqser.inventario.adapters.mysql.daos;

import com.paqser.inventario.adapters.mysql.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
