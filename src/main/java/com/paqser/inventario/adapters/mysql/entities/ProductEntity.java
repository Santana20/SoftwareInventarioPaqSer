package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.Product;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name="Product")
public class ProductEntity {
    @Id
    private String idProduct;

    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "idBrand")
    private BrandEntity brand;

    @ManyToOne
    @JoinColumn(name = "idProductType")
    private ProductTypeEntity productType;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<DetailProductEntity> detailProductEntityList;

    public ProductEntity() {
        // empty for framework
    }

    public ProductEntity(Product product) {
        BeanUtils.copyProperties(product, this);
    }

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

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public List<DetailProductEntity> getDetailProductEntityList() {
        return detailProductEntityList;
    }

    public void setDetailProductEntityList(List<DetailProductEntity> detailProductEntityList) {
        this.detailProductEntityList = detailProductEntityList;
    }

    public void fromProduct(Product product) {
        BeanUtils.copyProperties(product, this);
    }

    public Product toProduct() {
        Product product = new Product();
        BeanUtils.copyProperties(this, product);
        // product.setIdBrand(this.brand.getIdBrand());
        // product.setIdProductType(this.productType.getIdProductType());
        product.setBrand(this.brand.toBrand());
        product.setProductType(this.productType.toProductType());
        if (this.detailProductEntityList != null)
            product.setDetailProductsList(this.detailProductEntityList.stream().map(
                DetailProductEntity::toDetailProduct
            ).collect(Collectors.toList()));
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return idProduct.equals(that.idProduct) &&
                Objects.equals(nameProduct, that.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "idProduct='" + idProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", Brand=" + brand +
                ", productType=" + productType +
                '}';
    }
}
