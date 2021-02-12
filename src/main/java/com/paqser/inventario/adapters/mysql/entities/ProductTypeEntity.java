package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.Product;
import com.paqser.inventario.domain.models.ProductType;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ProductType")
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductType;
    private String nameProductType;

    public ProductTypeEntity() {
        // empty for framework
    }

    public ProductTypeEntity(ProductType productType) {
        this.fromProductType(productType);
    }

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

    public void fromProductType(ProductType productType) {
        BeanUtils.copyProperties(productType, this);
    }

    public ProductType toProductType() {
        ProductType productType = new ProductType();
        BeanUtils.copyProperties(this, productType);
        return productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTypeEntity that = (ProductTypeEntity) o;
        return idProductType.equals(that.idProductType) &&
                Objects.equals(nameProductType, that.nameProductType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductType);
    }

    @Override
    public String toString() {
        return "ProductTypeEntity{" +
                "idProductType=" + idProductType +
                ", nameProductType='" + nameProductType + '\'' +
                '}';
    }
}
