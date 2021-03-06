package com.paqser.inventario.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
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

}
