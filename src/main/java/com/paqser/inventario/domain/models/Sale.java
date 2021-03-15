package com.paqser.inventario.domain.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Sale {

    private Long idSale;
    private String nameClient;
    private Date dateSale;
    private BigDecimal total;
    private boolean status;
    private List<DetailSale> detailSaleList;

    public Sale() {
        // empty for framework
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", nameClient='" + nameClient + '\'' +
                ", dateSale=" + dateSale +
                ", detailSaleList=" + detailSaleList +
                ", total=" + total +
                '}';
    }
}
