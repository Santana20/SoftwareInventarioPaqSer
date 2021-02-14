package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailSale;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "DetailSale")
public class DetailSaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailSale;
    private BigDecimal saleCount;
    private BigDecimal subTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idDetailProduct", nullable = false)
    private DetailProductEntity detailProductEntity;

    @ManyToOne
    @JoinColumn(name = "idSale")
    private SaleEntity saleEntity;

    public DetailSaleEntity() {
        // empty for framework
    }

    public DetailSaleEntity(DetailSale detailSale,
                            DetailProductEntity detailProductEntity,
                            SaleEntity saleEntity) {
        fromDetailSale(detailSale);
        setSaleEntity(saleEntity);
        setDetailProductEntity(detailProductEntity);
    }

    public void fromDetailSale(DetailSale detailSale) {
        BeanUtils.copyProperties(detailSale, this);
    }

    public Long getIdDetailSale() {
        return idDetailSale;
    }

    public void setIdDetailSale(Long idDetailSale) {
        this.idDetailSale = idDetailSale;
    }

    public BigDecimal getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(BigDecimal saleCount) {
        this.saleCount = saleCount;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public DetailProductEntity getDetailProductEntity() {
        return detailProductEntity;
    }

    public void setDetailProductEntity(DetailProductEntity detailProductEntity) {
        this.detailProductEntity = detailProductEntity;
    }

    public SaleEntity getSaleEntity() {
        return saleEntity;
    }

    public void setSaleEntity(SaleEntity saleEntity) {
        this.saleEntity = saleEntity;
    }

    @Override
    public String toString() {
        return "DetailSaleEntity{" +
                "idDetailSale=" + idDetailSale +
                ", saleCount=" + saleCount +
                ", subTotal=" + subTotal +
                ", detailProductEntity=" + detailProductEntity +
                ", saleEntity=" + saleEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailSaleEntity that = (DetailSaleEntity) o;
        return idDetailSale.equals(that.idDetailSale) &&
                Objects.equals(saleCount, that.saleCount) &&
                Objects.equals(subTotal, that.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetailSale);
    }

}
