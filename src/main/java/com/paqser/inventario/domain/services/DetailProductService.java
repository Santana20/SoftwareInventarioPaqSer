package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.persistencePorts.DetailProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class DetailProductService {

    private DetailProductPersistence detailProductPersistence;

    @Autowired
    public DetailProductService(DetailProductPersistence detailProductPersistence) {
        this.detailProductPersistence = detailProductPersistence;
    }

    public DetailProduct createDetailProduct(DetailProduct detailProduct) {
        return this.detailProductPersistence.createDetailProduct(detailProduct);
    }

    public Stream<DetailProduct> findAllDetailProductByIdProduct(String idProduct) {
        return this.detailProductPersistence.findAllDetailProductByIdProduct(idProduct);
    }
}
