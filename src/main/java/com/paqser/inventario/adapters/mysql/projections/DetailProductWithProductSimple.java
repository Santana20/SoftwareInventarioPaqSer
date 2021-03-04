package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.DetailProduct;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public interface DetailProductWithProductSimple
{
    Long getIdDetailProduct();
    String getNetContent();
    BigDecimal getStock();
    String getSaleUnit();
    BigDecimal getSalePrice();
    ProductWithoutDetailProducts getProductEntity();
    default DetailProduct toDetailProduct()
    {
        DetailProduct detailProduct = new DetailProduct();
        BeanUtils.copyProperties(this, detailProduct);
        detailProduct.setProduct(getProductEntity().toProduct());

        return detailProduct;
    }
}
