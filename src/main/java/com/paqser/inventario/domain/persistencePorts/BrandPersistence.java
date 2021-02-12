package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Brand;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BrandPersistence {

    Brand createBrand(Brand newBrand);

    Brand updateBrand(Long idBrand, Brand updatedBrand);

    Stream<Brand> listAllBrands();

}
