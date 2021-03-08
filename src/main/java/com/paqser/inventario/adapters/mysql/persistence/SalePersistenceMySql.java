package com.paqser.inventario.adapters.mysql.persistence;

import com.paqser.inventario.adapters.mysql.projections.DetailProductSimple;
import com.paqser.inventario.adapters.mysql.projections.SaleWithoutCycles;
import com.paqser.inventario.adapters.mysql.projections.SaleWithoutDetailSaleList;
import com.paqser.inventario.adapters.mysql.daos.DetailProductRepository;
import com.paqser.inventario.adapters.mysql.daos.DetailSaleRepository;
import com.paqser.inventario.adapters.mysql.daos.SaleRepository;
import com.paqser.inventario.adapters.mysql.entities.DetailSaleEntity;
import com.paqser.inventario.adapters.mysql.entities.SaleEntity;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.persistencePorts.SalePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

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

            DetailProductSimple detailProductSimple = this.detailProductRepository
                    .findByIdDetailProduct(detailSale.getIdDetailProduct(), DetailProductSimple.class);

            if (validateDetailSale(i, detailSale, detailProductSimple)) {

                detailSaleEntities.add(new DetailSaleEntity(detailSale, detailProductSimple.toDetailProductEntity(), saleEntity));
                // update stock of current detailProduct.
                this.detailProductRepository.updateStockById(detailProductSimple.getIdDetailProduct(),
                        detailProductSimple.getStock().subtract(detailSale.getSaleCount()));

                total = total.add(detailSale.getSubTotal());
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

    @Override
    public Stream<Sale> listSales(boolean isPDF) {
        if (isPDF) {
            return this.saleRepository.findAllBy(SaleWithoutCycles.class)
                    .stream().map(SaleWithoutCycles::toSale);
        }
        return this.saleRepository.findAllBy(SaleWithoutDetailSaleList.class)
                .stream().map(SaleWithoutDetailSaleList::toSale);
    }

    @Override
    public Stream<Sale> listSalesByDate(Date ini, Date fin, boolean isPDF) {
        if (isPDF) {
            return this.saleRepository
                    .findAllByDateSaleBetween(ini, fin, SaleEntity.class).stream()
                    .map(SaleEntity::toSale);
        }
        return this.saleRepository
                .findAllByDateSaleBetween(ini, fin, SaleWithoutDetailSaleList.class)
                .stream().map(SaleWithoutDetailSaleList::toSale);
    }

    @Override
    public Sale findSaleByIdSale(Long idSale) {

        return this.saleRepository.findByIdSale(idSale, SaleWithoutCycles.class)
                .toSale();
    }

    private boolean validateDetailSale(int index, DetailSale detailSale,
                                                   DetailProductSimple detailProductSimple) {

        if (detailProductSimple == null)
            throw new RuntimeException("No existe el detalle de producto con codigo: ".concat(String.valueOf(detailSale.getIdDetailProduct())));

        if (detailSale.getSaleCount().compareTo(detailProductSimple.getStock()) > 0)
            throw new RuntimeException("El stock es insuficiente para realizar la venta.\n" +
                    "indice del detalle de venta: " + index + "\n" +
                    "Stock pedido: " + detailSale.getSaleCount() + "\n" +
                    "Stock disponible: " + detailProductSimple.getStock());

        BigDecimal subtotal = detailProductSimple.getSalePrice()
                .multiply(detailSale.getSaleCount());

        if (!detailSale.getSubTotal().equals(subtotal))
            throw new RuntimeException("Hubo un error al calcular el subtotal del detalle de venta.\n" +
                    "indice del detalle de venta: " + index + "\n" +
                    "subtotal venta: " + detailSale.getSubTotal() + "\n" +
                    "subtotal calculada: " + subtotal);

        return true;

    }

}
