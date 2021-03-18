package com.paqser.inventario.adapters.mysql.entities;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.models.Purchase;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "DetailPurchase")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class DetailPurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailPurchase;

    private BigDecimal quantity;

    private BigDecimal subTotal;

    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "idDetailProduct")
    private DetailProductEntity detailProductEntity;

    @ManyToOne
    @JoinColumn(name = "idPurchase")
    private PurchaseEntity purchaseEntity;

    public DetailPurchaseEntity(DetailPurchase detailPurchase,
                                DetailProductEntity detailProductEntity,
                                PurchaseEntity purchaseEntity)
    {
        this.fromDetailPurchase(detailPurchase);
        this.setPurchaseEntity(purchaseEntity);
        this.setDetailProductEntity(detailProductEntity);
    }

    public void fromDetailPurchase(DetailPurchase detailPurchase)
    {
        BeanUtils.copyProperties(detailPurchase, this);
    }

}
