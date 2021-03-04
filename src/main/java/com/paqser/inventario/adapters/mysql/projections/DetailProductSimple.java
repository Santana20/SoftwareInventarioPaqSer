package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import lombok.Value;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Value
public class DetailProductSimple {
    Long idDetailProduct;
    BigDecimal stock, salePrice;

    public DetailProductEntity toDetailProductEntity()
    {
        DetailProductEntity detailProductEntity = new DetailProductEntity();
        BeanUtils.copyProperties(this, detailProductEntity);

        return detailProductEntity;
    }
}
