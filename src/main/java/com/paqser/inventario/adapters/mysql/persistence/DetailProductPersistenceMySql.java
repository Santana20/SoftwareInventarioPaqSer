package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.ProductRepository;
import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import com.paqser.inventario.adapters.mysql.projections.DetailProductWithoutForeignClass;
import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.persistencePorts.DetailProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository("DetailProductPersistence")
public class DetailProductPersistenceMySql implements DetailProductPersistence {

    private final DetailProductRepository detailProductRepository;

    private final ProductRepository productRepository;

    @Autowired
    public DetailProductPersistenceMySql(DetailProductRepository detailProductRepository, ProductRepository productRepository) {
        this.detailProductRepository = detailProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DetailProduct createDetailProduct(DetailProduct detailProduct) {
        if (detailProduct.getIdProduct() == null)
            throw new RuntimeException("Debe ingresar el codigo del producto para registrar la presentacion de producto.");

        Optional<ProductEntity> product = this.productRepository.findById(detailProduct.getIdProduct());

        if (!product.isPresent()) {
            throw new RuntimeException("Producto no existe.");
        }

        return this.detailProductRepository
                .save(new DetailProductEntity(detailProduct, product.get()))
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
    public Stream<DetailProduct> findAllDetailProductByIdProduct(Long idProduct) {
        return this.detailProductRepository
                .findAllByProductEntity_Id(idProduct, DetailProductWithoutForeignClass.class)
                .stream().map(DetailProductWithoutForeignClass::toDetailProduct);
    }
}
