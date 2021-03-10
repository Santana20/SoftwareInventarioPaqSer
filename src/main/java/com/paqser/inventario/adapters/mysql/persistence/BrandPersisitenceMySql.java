package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.BrandRepository;
import com.paqser.inventario.adapters.mysql.entities.BrandEntity;
import com.paqser.inventario.domain.models.Brand;
import com.paqser.inventario.domain.persistencePorts.BrandPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("BrandPersisitence")
public class BrandPersisitenceMySql implements BrandPersistence {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandPersisitenceMySql(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public boolean existIdBrand(Long idBrand) {
        return this.brandRepository.existsById(idBrand);
    }

    @Override
    public Brand createBrand(Brand newBrand) {

        if (newBrand.getIdBrand() != null && this.existIdBrand(newBrand.getIdBrand()))
            throw new RuntimeException("Marca con codigo <" + newBrand.getIdBrand() + "> ya fue registrado");

        return this.brandRepository
                .save(new BrandEntity(newBrand))
                .toBrand();
    }

    @Override
    public Brand updateBrand(Long idBrand, Brand updatedBrand) {
        return null;
    }

    @Override
    public Stream<Brand> listAllBrands() {
        return this.brandRepository.findAll().stream()
                .map(BrandEntity::toBrand);
    }
}
