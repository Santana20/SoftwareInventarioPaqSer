package com.paqser.inventario.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DetailPurchase {

    private Long idDetailPurchase;
    private BigDecimal quantity;
    private BigDecimal subTotal;
    private BigDecimal unitPrice;
    private DetailProduct detailProduct;
    private Purchase purchase;

    public Long getIdDetailProduct() {
        if (this.detailProduct == null) return null;
        return this.detailProduct.getIdDetailProduct();
    }
}
