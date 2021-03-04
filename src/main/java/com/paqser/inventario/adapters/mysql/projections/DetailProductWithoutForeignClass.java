package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.DetailProduct;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public interface DetailProductWithoutForeignClass {
    Long getIdDetailProduct();
    String getNetContent();
    BigDecimal getStock();
    String getSaleUnit();
    BigDecimal getSalePrice();
    default DetailProduct toDetailProduct()
    {
        DetailProduct detailProduct = new DetailProduct();
        BeanUtils.copyProperties(this, detailProduct);

        return detailProduct;
    }
}
