package com.paqser.inventario.adapters.utils.DTOClass;

import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalePDF {
    private Long idSale;
    private String nameClient;
    private Date dateSale;
    private BigDecimal total;
    private List<DetailSalePDF> detailSalePDFList;

    public SalePDF()
    {
        // empty for framework
    }
    public SalePDF(Sale sale)
    {
        BeanUtils.copyProperties(sale, this);
        List<DetailSalePDF> list = new ArrayList<>();
        for (DetailSale detailSale : sale.getDetailSaleList()) {
            DetailSalePDF detailSalePDF = new DetailSalePDF(detailSale);
            list.add(detailSalePDF);
        }
        this.setDetailSalePDFList(list);
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

    public List<DetailSalePDF> getDetailSalePDFList() {
        return detailSalePDFList;
    }

    public void setDetailSalePDFList(List<DetailSalePDF> detailSalePDFList) {
        this.detailSalePDFList = detailSalePDFList;
    }
}
