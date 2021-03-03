package com.paqser.inventario.domain.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Sale {

    private Long idSale;
    private String nameClient;
    private Date dateSale;
    private BigDecimal total;
    private List<DetailSale> detailSaleList;

    public Sale() {
        // empty for framework
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

    public List<DetailSale> getDetailSaleList() {
        return detailSaleList;
    }

    public void setDetailSaleList(List<DetailSale> detailSaleList) {
        this.detailSaleList = detailSaleList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", nameClient='" + nameClient + '\'' +
                ", dateSale=" + dateSale +
                ", detailSaleList=" + detailSaleList +
                ", total=" + total +
                '}';
    }
}
