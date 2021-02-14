package com.paqser.inventario.domain.models;

public class ProductType {
    private Long idProductType;
    private String nameProductType;

    public Long getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(Long idProductType) {
        this.idProductType = idProductType;
    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "idProductType=" + idProductType +
                ", nameProductType='" + nameProductType + '\'' +
                '}';
    }

}
