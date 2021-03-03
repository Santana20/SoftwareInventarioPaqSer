package com.paqser.inventario.domain.models;

import java.math.BigDecimal;

public class DetailSale {

    private Long idDetailSale;
    private BigDecimal saleCount;
    private BigDecimal subTotal;
    private DetailProduct detailProduct;
    private Sale sale;

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

    public DetailProduct getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(DetailProduct detailProduct) {
        this.detailProduct = detailProduct;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Long getIdDetailProduct()
    {
        if (this.detailProduct == null) return null;
        return this.detailProduct.getIdDetailProduct();
    }

    @Override
    public String toString() {
        return "detailSale{" +
                "idDetailSale=" + idDetailSale +
                ", saleCount=" + saleCount +
                ", subTotal=" + subTotal +
                '}';
    }
}
