package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.adapters.utils.DTOClass.DetailSalePDF;
import com.paqser.inventario.domain.services.DetailSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(DetailSaleResource.DETAILSALE)
public class DetailSaleResource {

    static final String DETAILSALE = "/api/detailSale";
    static final String FINDALLDETAILSALEBYIDSALE = "/listDS/{idSale}";

    private final DetailSaleService detailSaleService;

    @Autowired
    public DetailSaleResource(DetailSaleService detailSaleService) {
        this.detailSaleService = detailSaleService;
    }

    @GetMapping(DetailSaleResource.FINDALLDETAILSALEBYIDSALE)
    public Stream<DetailSalePDF> findAllDetailSaleByIdSale(@PathVariable("idSale") Long idSale)
    {
        Stream<DetailSalePDF> detailSaleStream;
        try
        {
            detailSaleStream = this.detailSaleService.findAllDetailSaleByIdSale(idSale)
                                .map(DetailSalePDF::new);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return detailSaleStream;
    }
}
