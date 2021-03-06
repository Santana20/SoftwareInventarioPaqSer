package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.adapters.mysql.entities.BrandEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductTypeEntity;
import com.paqser.inventario.domain.models.Product;
import org.springframework.beans.BeanUtils;

public interface ProductWithoutDetailProducts
{
    Long getId();
    String getCodProduct();
    String getNameProduct();
    BrandEntity getBrand();
    ProductTypeEntity getProductType();
    default Product toProduct()
    {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        product.setBrand(getBrand().toBrand());
        product.setProductType(getProductType().toProductType());

        return product;
    }
    default ProductEntity toProductEntity()
    {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(this, productEntity);

        return productEntity;
    }
}
