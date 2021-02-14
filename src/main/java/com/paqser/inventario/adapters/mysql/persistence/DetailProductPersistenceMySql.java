package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.ProductRepository;
import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.persistencePorts.DetailProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("DetailProductPersistence")
public class DetailProductPersistenceMySql implements DetailProductPersistence {

    private DetailProductRepository detailProductRepository;

    private ProductRepository productRepository;

    @Autowired
    public DetailProductPersistenceMySql(DetailProductRepository detailProductRepository, ProductRepository productRepository) {
        this.detailProductRepository = detailProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DetailProduct createDetailProduct(DetailProduct detailProduct) {
        ProductEntity product = this.productRepository.findByIdProduct(detailProduct.getIdProduct());

        if (product == null)
            throw new RuntimeException("Producto con codigo <" + detailProduct.getIdProduct() + "> no existe.");

        return this.detailProductRepository
                .save(new DetailProductEntity(detailProduct, product))
                .toDetailProduct();
    }

    @Override
    public DetailProduct updateDetailProduct(String idDetailProduct, DetailProduct detailProduct) {
        return null;
    }

    @Override
    public DetailProduct deleteDetailProductById(String idDetailProduct) {
        return null;
    }

    @Override
    public Stream<DetailProduct> listDetailProductsByIdProduct(String idProduct) {
        return null;
    }
}
