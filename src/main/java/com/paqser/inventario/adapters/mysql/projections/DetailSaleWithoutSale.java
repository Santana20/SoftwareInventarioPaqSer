package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.DetailSale;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public interface DetailSaleWithoutSale {
    Long getIdDetailSale();
    BigDecimal getSaleCount();
    BigDecimal getSubTotal();
    DetailProductWithProductSimple getDetailProductEntity();

    default DetailSale toDetailSale()
    {
        DetailSale detailSale = new DetailSale();
        BeanUtils.copyProperties(this, detailSale);
        detailSale.setDetailProduct(getDetailProductEntity().toDetailProduct());
        return detailSale;
    }
}
