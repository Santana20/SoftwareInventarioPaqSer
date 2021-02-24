package com.paqser.inventario.domain.models;

import java.util.List;

public class Product {
    private String idProduct;
    private String nameProduct;
    private Brand brand;
    private ProductType productType;
    private List<DetailProduct> detailProductsList;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    public Long getIdBrand()
    {
        if (this.brand == null) return null;
        return this.brand.getIdBrand();
    }
    public Long getIdProductType()
    {
        if (this.productType == null) return null;
        return this.productType.getIdProductType();
    }
    public List<DetailProduct> getDetailProductsList() {
        return detailProductsList;
    }

    public void setDetailProductsList(List<DetailProduct> detailProductsList) {
        this.detailProductsList = detailProductsList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                '}';
    }
}
