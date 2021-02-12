package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(SaleResource.SALE)
public class SaleResource {

    static final String SALE = "/api/sale";

    private final SaleService saleService;

    @Autowired
    public SaleResource(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale registerSale(@RequestBody Sale paramSale)
    {
        Sale sale;
        System.out.println(paramSale);
        try
        {
            sale = this.saleService.registerSale(paramSale);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return sale;
    }

}
