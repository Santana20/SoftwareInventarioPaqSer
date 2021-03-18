package com.paqser.inventario.adapters.utils.DTOClass;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DetailPurchasePDF {
    String codProduct;
    String description;
    BigDecimal quantity;
    BigDecimal subTotal;
    String purchaseUnit;
    BigDecimal unitPrice;

    public DetailPurchasePDF(DetailPurchase detailPurchase) {
        this.quantity = detailPurchase.getQuantity();
        this.subTotal = detailPurchase.getSubTotal();
        this.unitPrice = detailPurchase.getUnitPrice();
        DetailProduct detailProduct = detailPurchase.getDetailProduct();
        if (detailProduct != null)
        {
            this.purchaseUnit = detailProduct.getSaleUnit();
            Product product = detailProduct.getProduct();
            if (product != null)
            {
                this.description = String.format("%s / %s", product.description(), detailProduct.getNetContent());
                this.codProduct = product.getCodProduct();
            }
        }
    }
}
