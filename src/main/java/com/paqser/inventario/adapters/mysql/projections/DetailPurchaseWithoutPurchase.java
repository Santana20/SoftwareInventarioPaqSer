package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.DetailPurchase;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public interface DetailPurchaseWithoutPurchase {
    Long getIdDetailPurchase();
    BigDecimal getQuantity();
    BigDecimal getSubTotal();
    DetailProductWithProductSimple getDetailProductEntity();

    default DetailPurchase toDetailPurchase()
    {
        DetailPurchase detailPurchase = new DetailPurchase();

        BeanUtils.copyProperties(this, detailPurchase);
        detailPurchase.setDetailProduct(getDetailProductEntity().toDetailProduct());

        return detailPurchase;
    }
}
