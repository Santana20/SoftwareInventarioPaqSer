package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailProduct;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Detail_Product")
public class DetailProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailProduct;
    private String netContent;
    private BigDecimal stock;
    private String saleUnit;
    private BigDecimal salePrice;

    @ManyToOne
    @JoinColumn(name = "idProduct")
    private ProductEntity productEntity;

    public DetailProductEntity() {
        // empty for framework
    }

    public DetailProductEntity(DetailProduct detailProduct, ProductEntity productEntity) {
        this.fromDetailProduct(detailProduct);
        this.productEntity = productEntity;
    }

    public Long getIdDetailProduct() {
        return idDetailProduct;
    }

    public void setIdDetailProduct(Long idDetailProduct) {
        this.idDetailProduct = idDetailProduct;
    }

    public String getNetContent() {
        return netContent;
    }

    public void setNetContent(String netContent) {
        this.netContent = netContent;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity product) {
        this.productEntity = product;
    }

    public void fromDetailProduct(DetailProduct detailProduct) {
        BeanUtils.copyProperties(detailProduct, this);
    }

    public DetailProduct toDetailProduct() {
        DetailProduct detailProduct = new DetailProduct();
        BeanUtils.copyProperties(this, detailProduct);

        detailProduct.setProduct(this.productEntity.toProductForDetailProductConstruct());

        return detailProduct;
    }

    public DetailProduct toDetailProductForProductConstruct()
    {
        DetailProduct detailProduct = new DetailProduct();
        BeanUtils.copyProperties(this, detailProduct);

        return detailProduct;
    }

    @Override
    public String toString() {
        return "DetailProductEntity{" +
                "idDetailProduct=" + idDetailProduct +
                ", netContent='" + netContent + '\'' +
                ", stock=" + stock +
                ", saleUnit='" + saleUnit + '\'' +
                ", salePrice=" + salePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailProductEntity that = (DetailProductEntity) o;
        return idDetailProduct.equals(that.idDetailProduct) &&
                Objects.equals(netContent, that.netContent) &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(salePrice, that.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailProduct);
    }
}
