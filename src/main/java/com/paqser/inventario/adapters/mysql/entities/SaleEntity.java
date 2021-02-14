package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.Sale;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sale")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;

    private String nameClient;

    private Date dateSale;

    private BigDecimal total;

    @OneToMany(mappedBy = "saleEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<DetailSaleEntity> detailSaleEntityList;

    public SaleEntity() {
        // empty for framework
    }

    public SaleEntity(Sale sale) {
        fromSale(sale);
    }

    public void fromSale(Sale sale) {
        BeanUtils.copyProperties(sale, this);
    }

    public Sale toSale() {
        Sale sale = new Sale();
        BeanUtils.copyProperties(this, sale);
        return sale;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
        this.idSale = idSale;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetailSaleEntity> getDetailSaleEntityList() {
        return detailSaleEntityList;
    }

    public void setDetailSaleEntityList(List<DetailSaleEntity> detailSaleEntityList) {
        this.detailSaleEntityList = detailSaleEntityList;
    }

    @Override
    public String toString() {
        return "SaleEntity{" +
                "idSale=" + idSale +
                ", nameClient='" + nameClient + '\'' +
                ", dateSale=" + dateSale +
                ", total=" + total +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleEntity that = (SaleEntity) o;
        return idSale.equals(that.idSale) &&
                Objects.equals(nameClient, that.nameClient) &&
                Objects.equals(dateSale, that.dateSale) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSale);
    }
}
