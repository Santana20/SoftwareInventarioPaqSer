package com.paqser.inventario.adapters.utils.DTOClass;

import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import com.paqser.inventario.adapters.mysql.entities.DetailSaleEntity;
import com.paqser.inventario.adapters.mysql.entities.ProductEntity;

import java.math.BigDecimal;

public class DetailSalePDF {

    private String code;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal count;
    private BigDecimal subTotal;
    public DetailSalePDF(DetailSaleEntity detailSaleEntity) {
        this.subTotal = detailSaleEntity.getSubTotal();
        this.count = detailSaleEntity.getSaleCount();
        DetailProductEntity detailProductEntity = detailSaleEntity.getDetailProductEntity();
        if (detailProductEntity != null)
        {
            this.unitPrice = detailProductEntity.getSalePrice();
            ProductEntity productEntity = detailProductEntity.getProduct();
            if (productEntity != null)
            {
                this.description = productEntity.getNameProduct() + " / " +
                                    detailProductEntity.getNetContent();
                this.code = productEntity.getIdProduct();
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
