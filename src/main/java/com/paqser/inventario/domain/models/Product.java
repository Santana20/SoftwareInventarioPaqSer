package com.paqser.inventario.domain.models;

import java.util.List;

public class Product {
    private String idProduct;
    private String nameProduct;
    private Long idBrand;
    private Long idProductType;
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

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public Long getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(Long idProductType) {
        this.idProductType = idProductType;
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
                ", idBrand=" + idBrand +
                ", detailProductsList=" + detailProductsList +
                '}';
    }
}
