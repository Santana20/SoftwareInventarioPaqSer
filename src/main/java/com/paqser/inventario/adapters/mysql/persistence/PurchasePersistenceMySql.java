package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.DetailPurchaseRepository;
import com.paqser.inventario.adapters.mysql.daos.PurchaseRepository;
import com.paqser.inventario.adapters.mysql.entities.DetailPurchaseEntity;
import com.paqser.inventario.adapters.mysql.entities.PurchaseEntity;
import com.paqser.inventario.adapters.mysql.projections.DetailProductSimple;
import com.paqser.inventario.adapters.mysql.projections.PurchaseWithoutDetailPurchaseList;
import com.paqser.inventario.domain.models.DetailPurchase;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Purchase;
import com.paqser.inventario.domain.persistencePorts.PurchasePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Repository("PurchasePersistence")
public class PurchasePersistenceMySql implements PurchasePersistence {

    private final PurchaseRepository purchaseRepository;
    private final DetailProductRepository detailProductRepository;
    private final DetailPurchaseRepository detailPurchaseRepository;

    @Autowired
    public PurchasePersistenceMySql(PurchaseRepository purchaseRepository, DetailProductRepository detailProductRepository, DetailPurchaseRepository detailPurchaseRepository) {
        this.purchaseRepository = purchaseRepository;
        this.detailProductRepository = detailProductRepository;
        this.detailPurchaseRepository = detailPurchaseRepository;
    }


    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Purchase registerPurchase(Purchase purchase)
    {

        purchase.setDatePurchase(new Date());
        PurchaseEntity purchaseEntity = this.purchaseRepository
                .save(new PurchaseEntity(purchase));

        List<DetailPurchaseEntity> detailPurchaseEntities = new ArrayList<>();

        BigDecimal total = BigDecimal.valueOf(0);
        int i = 0;


        for (DetailPurchase detailPurchase : purchase.getDetailPurchaseList())
        {

            DetailProductSimple detailProductSimple = this.detailProductRepository
                    .findByIdDetailProduct(detailPurchase.getIdDetailProduct(), DetailProductSimple.class);

            if (validateDetailPurchase(i, detailPurchase, detailProductSimple))
            {
                detailPurchaseEntities.add(new DetailPurchaseEntity(detailPurchase,
                        detailProductSimple.toDetailProductEntity(),
                        purchaseEntity));

                // Se aumenta el stock del producto.
                this.detailProductRepository.updateStockById(detailProductSimple.getIdDetailProduct(),
                        detailProductSimple.getStock().add(detailPurchase.getQuantity()));

            }
            total = total.add(detailPurchase.getSubTotal());
            i += 1;
        }

        total = total.setScale(2, RoundingMode.HALF_UP);

        if (!purchase.getTotal().equals(total)) {
            throw new RuntimeException("Hubo un error al calcular el total de la compra.\n" +
                    "total compra: " + purchase.getTotal() + "\n" +
                    "total calculada: " + total);
        }

        this.detailPurchaseRepository.saveAll(detailPurchaseEntities);

        return purchaseEntity.toPurchase();

    }

    @Override
    public Stream<Purchase> listSalesByDate(Date dateIni, Date dateFin) {

        return this.purchaseRepository
                .findAllByDatePurchaseBetween(dateIni, dateFin, PurchaseWithoutDetailPurchaseList.class)
                .stream().map(PurchaseWithoutDetailPurchaseList::toPurchase);
    }

    @Override
    public Stream<Purchase> listPurchases() {
        return this.purchaseRepository
                .findAllBy(PurchaseWithoutDetailPurchaseList.class)
                .stream().map(PurchaseWithoutDetailPurchaseList::toPurchase);
    }

    private boolean validateDetailPurchase(int index, DetailPurchase detailPurchase,
                                       DetailProductSimple detailProductSimple) {

        if (detailProductSimple == null)
            throw new RuntimeException("No existe el detalle de producto con codigo: ".concat(String.valueOf(detailPurchase.getIdDetailProduct())));



        BigDecimal subtotal = detailPurchase.getUnitPrice()
                .multiply(detailPurchase.getQuantity()).setScale(2, RoundingMode.HALF_UP);;

        if (!detailPurchase.getSubTotal().equals(subtotal))
            throw new RuntimeException("Hubo un error al calcular el subtotal del detalle de venta.\n" +
                    "indice del detalle de venta: " + index + "\n" +
                    "subtotal venta: " + detailPurchase.getSubTotal() + "\n" +
                    "subtotal calculada: " + subtotal);

        return true;

    }

}
