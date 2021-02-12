package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Brand;
import com.paqser.inventario.domain.persistencePorts.BrandPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BrandService {

    private BrandPersistence brandPersistence;

    @Autowired
    public BrandService(BrandPersistence brandPersistence) {
        this.brandPersistence = brandPersistence;
    }

    public Brand createBrand(Brand newBrand) {
        return this.brandPersistence.createBrand(newBrand);
    }

    public Stream<Brand> listAllBrands() {
        return this.brandPersistence.listAllBrands();
    }
}
