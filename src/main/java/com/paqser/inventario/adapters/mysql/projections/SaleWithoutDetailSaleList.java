package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.Sale;
import lombok.Value;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Value
public class SaleWithoutDetailSaleList {

    Long idSale;
    String nameClient;
    Date dateSale;
    BigDecimal total;

    public Sale toSale()
    {
        Sale sale = new Sale();
        BeanUtils.copyProperties(this, sale);
        return sale;
    }

}