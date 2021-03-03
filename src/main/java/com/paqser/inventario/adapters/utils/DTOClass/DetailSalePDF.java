package com.paqser.inventario.adapters.utils.DTOClass;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Product;

import java.math.BigDecimal;

public class DetailSalePDF {

    private String code;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal count;
    private BigDecimal subTotal;
    public DetailSalePDF(DetailSale detailSale) {
        this.subTotal = detailSale.getSubTotal();
        this.count = detailSale.getSaleCount();
        DetailProduct detailProduct = detailSale.getDetailProduct();
        if (detailProduct != null)
        {
            this.unitPrice = detailProduct.getSalePrice();
            Product product = detailProduct.getProduct();
            if (product != null)
            {
                this.description = product.getNameProduct() + " / " +
                                    detailProduct.getNetContent();
                this.code = product.getIdProduct();
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
