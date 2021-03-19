package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.BrandRepository;
import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.ProductRepository;
import com.paqser.inventario.adapters.mysql.daos.ProductTypeRepository;
import com.paqser.inventario.adapters.mysql.entities.BrandEntity;
import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductTypeEntity;
import com.paqser.inventario.adapters.mysql.projections.ProductWithoutDetailProducts;
import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.Product;
import com.paqser.inventario.domain.persistencePorts.ProductPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("ProductPersistence")
public class ProductPersistenceMySql implements ProductPersistence {

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final ProductTypeRepository productTypeRepository;

    private final DetailProductRepository detailProductRepository;

    @Autowired
    public ProductPersistenceMySql(ProductRepository productRepository, BrandRepository brandRepository, ProductTypeRepository productTypeRepository, DetailProductRepository detailProductRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.productTypeRepository = productTypeRepository;
        this.detailProductRepository = detailProductRepository;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Product createProduct(Product newProduct) {

        if (this.productRepository.existsByCodProductAndBrand_IdBrandAndProductType_IdProductTypeAndNameProduct(newProduct.getCodProduct(),
                newProduct.getIdBrand(), newProduct.getIdProductType(), newProduct.getNameProduct()))
            throw new RuntimeException("Ya existe un producto con dichos atributos");

        if (newProduct.getIdBrand() == null)
            throw new RuntimeException("Debe ingresar la marca del producto.");

        if (newProduct.getIdProductType() == null)
            throw new RuntimeException("Debe ingresar el tipo de producto.");

        Optional<BrandEntity> brandEntity = this.brandRepository.findById(newProduct.getIdBrand());

        if (!brandEntity.isPresent())
            throw new RuntimeException("No existe la marca con codigo: " + newProduct.getIdBrand());

        Optional<ProductTypeEntity> productTypeEntity = this.productTypeRepository.findById(newProduct.getIdProductType());

        if (!productTypeEntity.isPresent())
            throw new RuntimeException("No existe el tipo de producto con codigo: " + newProduct.getIdBrand());

        ProductEntity productEntity = new ProductEntity(newProduct);

        productEntity.setBrand(brandEntity.get());
        productEntity.setProductType(productTypeEntity.get());

        productEntity = this.productRepository
                .save(productEntity);

        if ( newProduct.getDetailProductsList() != null && (!newProduct.getDetailProductsList().isEmpty()) ) {
            List<DetailProductEntity> detailProductEntityList = new ArrayList<>();

            for (DetailProduct detailProduct : newProduct.getDetailProductsList()) {
                detailProduct.setStock(BigDecimal.ZERO);
                detailProductEntityList.add(new DetailProductEntity(detailProduct, productEntity));
            }

            this.detailProductRepository.saveAll(detailProductEntityList);
        }

        return productEntity.toProduct();
    }

    @Override
    public Product updateProduct(Product updateProduct) {
        return null;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        /* Todo
        ProductEntity product = this.productRepository.findByIdProduct(idProduct);

        if (product == null) {
            throw new RuntimeException("No existe producto con codigo < " + idProduct + ">");
        }
        */
        return null;
    }

    @Override
    public Stream<Product> listAllProducts() {
        return null;
    }

    @Override
    public Stream<Product> searchProducts(String nameProduct, Long idBrand, Long idProductType) {

        List<ProductWithoutDetailProducts> listProducts;

        if (idBrand != null && !this.brandRepository.existsById(idBrand))
            throw new RuntimeException("No existe la marca con codigo: " + idBrand);

        if (idProductType != null && !this.productTypeRepository.existsById(idProductType))
            throw new RuntimeException("No existe tipo de producto con codigo: " + idProductType);

        int bitsMaskSearch = 0;

        bitsMaskSearch += (nameProduct != null) ? (1 << 2) : 0;
        bitsMaskSearch += (idBrand != null) ? (1 << 1) : 0;
        bitsMaskSearch += (idProductType != null) ? 1 : 0;

        switch (bitsMaskSearch) {
            case 0:
                listProducts = this.productRepository.findAllBy(ProductWithoutDetailProducts.class);
                break;
            case 1:
                listProducts = this.productRepository
                        .findAllByProductType_IdProductType(idProductType, ProductWithoutDetailProducts.class);
                break;
            case 2:
                listProducts = this.productRepository
                        .findAllByBrand_IdBrand(idBrand, ProductWithoutDetailProducts.class);
                break;
            case 3:
                listProducts = this.productRepository
                        .findAllByBrand_IdBrandAndProductType_IdProductType(idBrand, idProductType, ProductWithoutDetailProducts.class);
                break;
            case 4:
                listProducts = this.productRepository
                        .findAllByNameProductContains(nameProduct, ProductWithoutDetailProducts.class);
                break;
            case 5:
                listProducts = this.productRepository
                        .findAllByNameProductContainsAndProductType_IdProductType(nameProduct, idProductType, ProductWithoutDetailProducts.class);
                break;
            case 6:
                listProducts = this.productRepository
                        .findAllByNameProductContainsAndBrand_IdBrand(nameProduct, idBrand, ProductWithoutDetailProducts.class);
                break;
            case 7:
                listProducts = this.productRepository
                        .findAllByNameProductContainsAndBrand_IdBrandAndProductType_IdProductType(nameProduct, idBrand, idProductType, ProductWithoutDetailProducts.class);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bitsMaskSearch +
                        "\nAun No se puede buscar con los parametros dados.");
        }

        return listProducts.stream()
                .map(ProductWithoutDetailProducts::toProduct);
    }
}
