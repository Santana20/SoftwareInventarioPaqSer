package com.paqser.inventario.domain.models;

import java.math.BigDecimal;

public class DetailProduct {

    private Long idDetailProduct;
    private String netContent;
    private BigDecimal stock;
    private String saleUnit;
    private BigDecimal salePrice;

    private Product product;

    public Long getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(Long idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }

    public String getNetContent() {
        return netContent;
    }

    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getIdProduct()
    {
        if (this.product == null) return null;
        return this.product.getId();
    }

    @Override
    public String toString() {
        return "DetailProduct{" +
                "idDetailProduct=" + idDetailProduct +
                ", netContent='" + netContent + '\'' +
                ", stock=" + stock +
                ", saleUnit='" + saleUnit + '\'' +
                ", salePrice=" + salePrice +
                '}';
    }
}
