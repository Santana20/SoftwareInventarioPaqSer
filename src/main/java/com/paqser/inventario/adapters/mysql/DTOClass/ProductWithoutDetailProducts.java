package com.paqser.inventario.adapters.mysql.DTOClass;

import com.paqser.inventario.adapters.mysql.entities.BrandEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductTypeEntity;
import com.paqser.inventario.domain.models.Product;
import lombok.Value;
import org.springframework.beans.BeanUtils;

@Value
public class ProductWithoutDetailProducts {
    String idProduct, nameProduct;
    BrandEntity brand;
    ProductTypeEntity productType;

    public Product toProduct()
    {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        product.setBrand(this.brand.toBrand());
        product.setProductType(this.productType.toProductType());

        return product;
    }
}
