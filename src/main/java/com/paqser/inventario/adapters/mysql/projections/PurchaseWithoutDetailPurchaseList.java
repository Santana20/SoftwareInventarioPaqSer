package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.Purchase;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

public interface PurchaseWithoutDetailPurchaseList {
    Long getIdPurchase();
    Date getDatePurchase();
    BigDecimal getTotal();
    String getRUC();

    default Purchase toPurchase()
    {
        Purchase purchase = new Purchase();

        BeanUtils.copyProperties(this, purchase);

        return purchase;
    }

}
