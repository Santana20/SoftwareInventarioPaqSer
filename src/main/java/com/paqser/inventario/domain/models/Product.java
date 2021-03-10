package com.paqser.inventario.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Product {
    private Long id;
    private String codProduct;
    private String nameProduct;
    private Brand brand;
    private ProductType productType;
    private List<DetailProduct> detailProductsList;

    @JsonIgnore
    public Long getIdBrand()
    {
        if (this.brand == null) return null;
        return this.brand.getIdBrand();
    }
    @JsonIgnore
    public Long getIdProductType()
    {
        if (this.productType == null) return null;
        return this.productType.getIdProductType();
    }

    public String description() {
        return String.format("%s / %s / %s",
                this.getNameProduct(),
                this.getBrand().getNameBrand(),
                this.getProductType().getNameProductType());
    }
}
