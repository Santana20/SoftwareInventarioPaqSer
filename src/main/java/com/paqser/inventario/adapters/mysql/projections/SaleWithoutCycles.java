package com.paqser.inventario.adapters.mysql.projections;

import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface SaleWithoutCycles {

    Long getIdSale();
    String getNameClient();
    Date getDateSale();
    BigDecimal getTotal();
    List<DetailSaleWithoutSale> getDetailSaleEntityList();

    default Sale toSale()
    {
        Sale sale = new Sale();

        BeanUtils.copyProperties(this, sale);
        List<DetailSale> list = new ArrayList<>();
        for (DetailSaleWithoutSale detailSaleWithoutSale : getDetailSaleEntityList()) {
            DetailSale toDetailSale = detailSaleWithoutSale.toDetailSale();
            list.add(toDetailSale);
        }
        sale.setDetailSaleList(list);

        return sale;
    }

}
