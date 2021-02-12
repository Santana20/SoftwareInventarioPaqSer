package com.paqser.inventario.domain.services;

import com.paqser.inventario.domain.models.Product;
import com.paqser.inventario.domain.persistencePorts.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ProductService {

    private ProductPersistence productPersistence;

    @Autowired
    public ProductService(ProductPersistence productPersistence) {
        this.productPersistence = productPersistence;
    }

    public Product createProduct(Product newProduct) throws Exception {
        return this.productPersistence.createProduct(newProduct);
    }

    public void updateProduct(Product updatedProduct) {
        Product product = this.productPersistence.findByIdProduct(updatedProduct.getIdProduct());

        if (product != null) {
            if (updatedProduct.getNameProduct() != null) product.setNameProduct(updatedProduct.getNameProduct());
            this.productPersistence.updateProduct(product);
        }
    }

    public Stream<Product> listAllProducts() {
        return this.productPersistence.listAllProducts();
    }

    public Stream<Product> searchProducts(String nameProduct, Long idBrand, Long idProductType) {
        return this.productPersistence.searchProducts(nameProduct, idBrand, idProductType);
    }

    public Product getProductById(String idProduct) {
        return this.productPersistence.findByIdProduct(idProduct);
    }
}
