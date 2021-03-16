package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.models.Purchase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Purchase")
@Getter
@Setter
@NoArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPurchase;

    private Date datePurchase;

    private BigDecimal total;
    @Column(length = 11)
    private String RUC;

    @OneToMany(mappedBy = "purchaseEntity",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<DetailPurchaseEntity> detailPurchaseEntityList;

    public PurchaseEntity(Purchase purchase)
    {
        this.fromPurchase(purchase);
    }

    public void fromPurchase(Purchase purchase)
    {
        BeanUtils.copyProperties(purchase, this);
    }

    public Purchase toPurchase() {
        Purchase purchase = new Purchase();
        BeanUtils.copyProperties(this, purchase);

        return purchase;
    }
}
