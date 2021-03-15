package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sale")
@Getter
@Setter
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSale;

    private String nameClient;

    private Date dateSale;

    private BigDecimal total;

    private boolean status;

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
        if (this.detailSaleEntityList != null && this.detailSaleEntityList.size() > 0) {
            List<DetailSale> detailSaleList = new ArrayList<>();
            for (DetailSaleEntity entity : this.detailSaleEntityList) {
                DetailSale toDetailSale = entity.toDetailSaleForSaleConstruct();
                detailSaleList.add(toDetailSale);
            }
            sale.setDetailSaleList(detailSaleList);
        }
        return sale;
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
