package com.paqser.inventario.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DetailSale {

    private Long idDetailSale;
    private BigDecimal saleCount;
    private BigDecimal subTotal;
    private DetailProduct detailProduct;
    private Sale sale;

    public DetailSale() {
        // empty for framework
    }

    public Long getIdDetailProduct()
    {
        if (this.detailProduct == null) return null;
        return this.detailProduct.getIdDetailProduct();
    }

}
