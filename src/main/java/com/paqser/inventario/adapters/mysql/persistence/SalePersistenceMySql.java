package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.DetailSaleRepository;
import com.paqser.inventario.adapters.mysql.daos.SaleRepository;
import com.paqser.inventario.adapters.mysql.entities.DetailProductEntity;
import com.paqser.inventario.adapters.mysql.entities.DetailSaleEntity;
import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.persistencePorts.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("SalePersistence")
public class SalePersistenceMySql implements SalePersistence {

    private final SaleRepository saleRepository;

    private final DetailProductRepository detailProductRepository;

    private final DetailSaleRepository detailSaleRepository;

    @Autowired
    public SalePersistenceMySql(SaleRepository saleRepository, DetailProductRepository detailProductRepository, DetailSaleRepository detailSaleRepository) {
        this.saleRepository = saleRepository;
        this.detailProductRepository = detailProductRepository;
        this.detailSaleRepository = detailSaleRepository;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public Sale registerSale(Sale sale) {
        
        sale.setDateSale(new Date());
        SaleEntity saleEntity = this.saleRepository
                .save(new SaleEntity(sale));

        List<DetailSaleEntity> detailSaleEntities = new ArrayList<>();

        BigDecimal total = BigDecimal.valueOf(0);
        int i = 0;

        for (DetailSale detailSale : sale.getDetailSaleList()) {

            DetailProductEntity detailProductEntity = this.detailProductRepository
                    .findByIdDetailProductSimple(detailSale.getIdDetailProduct());

            if (validateDetailSale(i, detailSale, detailProductEntity)) {

                detailSaleEntities.add(new DetailSaleEntity(detailSale, detailProductEntity, saleEntity));
                this.detailProductRepository.updateStockById(detailProductEntity.getIdDetailProduct(),
                        detailProductEntity.getStock().subtract(detailSale.getSaleCount()));
                total = total.add(sale.getDetailSaleList().get(i).getSubTotal());
                i += 1;

            }

        }

        if (!sale.getTotal().equals(total))
            throw new RuntimeException("Hubo un error al calcular el total de la venta.\n" +
                    "total venta: " + sale.getTotal() + "\n" +
                    "total calculada: " + total);

        this.detailSaleRepository.saveAll(detailSaleEntities);

        return saleEntity.toSale();

    }

    private boolean validateDetailSale(int index, DetailSale detailSale,
                                                   DetailProductEntity detailProductEntity) {

        if (detailProductEntity == null)
            throw new RuntimeException("No existe el detalle de producto con codigo: " + detailSale.getIdDetailProduct());

        if (detailSale.getSaleCount().compareTo(detailProductEntity.getStock()) > 0)
            throw new RuntimeException("El stock es insuficiente para realizar la venta.\n" +
                    "indice del detalle de venta: " + index + "\n" +
                    "Stock pedido: " + detailSale.getSaleCount() + "\n" +
                    "Stock disponible: " + detailProductEntity.getStock());

        BigDecimal subtotal = detailProductEntity.getSalePrice()
                .multiply(detailSale.getSaleCount());

        if (!detailSale.getSubTotal().equals(subtotal))
            throw new RuntimeException("Hubo un error al calcular el subtotal del detalle de venta.\n" +
                    "indice del detalle de venta: " + index + "\n" +
                    "subtotal venta: " + detailSale.getSubTotal() + "\n" +
                    "subtotal calculada: " + subtotal);

        return true;

    }

}
