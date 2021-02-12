package com.paqser.inventario.domain.models;

public class Brand {
    private Long idBrand;
    private String nameBrand;

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "idBrand=" + idBrand +
                ", nameBrand='" + nameBrand + '\'' +
                '}';
    }
}
