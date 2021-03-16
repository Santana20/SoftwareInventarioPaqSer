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
public class Purchase {

    private Long idPurchase;
    private Date datePurchase;
    private BigDecimal total;
    private String RUC;
    private List<DetailPurchase> detailPurchaseList;

    public Purchase() {
        // empty for framework
    }

}
