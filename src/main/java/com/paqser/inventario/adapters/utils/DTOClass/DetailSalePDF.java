package com.paqser.inventario.adapters.utils.DTOClass;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Getter
@Setter
@ToString
public class DetailSalePDF {

    private String codProduct;
    private String description;
    private BigDecimal unitPrice;
    private String saleUnit;
    private BigDecimal count;
    private BigDecimal subTotal;
    private Long idDetailProduct;
    public DetailSalePDF(DetailSale detailSale) {

        this.count = detailSale.getSaleCount();
        this.subTotal = detailSale.getSubTotal();
        DetailProduct detailProduct = detailSale.getDetailProduct();
        if (detailProduct != null)
        {
            this.idDetailProduct = detailProduct.getIdDetailProduct();
            this.unitPrice = detailProduct.getSalePrice();
            this.saleUnit = detailProduct.getSaleUnit();
            Product product = detailProduct.getProduct();
            if (product != null)
            {
                this.description = String.format("%s / %s", product.description(), detailProduct.getNetContent());
                this.codProduct = product.getCodProduct();
            }
        }

    }
}
