package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.Brand;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Brand")
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBrand;
    private String nameBrand;

    public BrandEntity() {
        // empty for framework
    }

    public BrandEntity(Brand brand) {
        this.fromBrand(brand);
    }

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

    public void fromBrand(Brand brand) {
        BeanUtils.copyProperties(brand, this);
    }

    public Brand toBrand() {
        Brand brand = new Brand();
        BeanUtils.copyProperties(this, brand);
        return brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandEntity that = (BrandEntity) o;
        return Objects.equals(idBrand, that.idBrand) &&
                Objects.equals(nameBrand, that.nameBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBrand);
    }

    @Override
    public String toString() {
        return "BrandEtity{" +
                "idBrand=" + idBrand +
                ", nameBrand='" + nameBrand + '\'' +
                '}';
    }
}
