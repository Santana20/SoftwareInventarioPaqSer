package com.paqser.inventario.domain.models;

import java.math.BigDecimal;

public class DetailSale {

    private Long idDetailSale;
    private BigDecimal saleCount;
    private BigDecimal subTotal;
    private Long idDetailProduct;
    private Long idSale;

    public DetailSale() {
        // empty for framework
    }

    public Long getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Long idDetailSale) {
        this.idDetailSale = idDetailSale;
    }

    public BigDecimal getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(BigDecimal saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public Long getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(Long idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    @Override
    public String toString() {
        return "detailSale{" +
                "idDetailSale=" + idDetailSale +
                ", saleCount=" + saleCount +
                ", subTotal=" + subTotal +
                ", idDetailProduct=" + idDetailProduct +
                ", idSale=" + idSale +
                '}';
    }
}
