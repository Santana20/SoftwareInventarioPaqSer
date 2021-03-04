package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.DetailProduct;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DetailProductPersistence {

    DetailProduct createDetailProduct(DetailProduct detailProduct);

    DetailProduct updateDetailProduct(String idDetailProduct, DetailProduct detailProduct);

    DetailProduct deleteDetailProductById(String idDetailProduct);

    Stream<DetailProduct> findAllDetailProductByIdProduct(String idProduct);
}
