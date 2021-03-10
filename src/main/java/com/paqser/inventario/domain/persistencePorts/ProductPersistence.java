package com.paqser.inventario.domain.persistencePorts;

import com.paqser.inventario.domain.models.Product;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductPersistence {

    Product createProduct(Product newProduct);

    Product updateProduct(Product updateProduct);

    Product findByIdProduct(String idProduct);

    Stream<Product> listAllProducts();

    Stream<Product> searchProducts(String nameProduct, Long idBrand, Long idProductType);

}
