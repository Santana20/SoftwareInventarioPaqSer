package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.Purchase;
import com.paqser.inventario.domain.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping(PurchaseResource.PURCHASE)
public class PurchaseResource {

    static final String PURCHASE = "/api/purchase";
    static final String LISTPURCHASES = "/list";

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseResource(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase registerPurchase(@RequestBody Purchase paramPurchase)
    {
        Purchase purchase;
        try
        {
            purchase = this.purchaseService.registerPurchase(paramPurchase);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return purchase;
    }

    @GetMapping(PurchaseResource.LISTPURCHASES)
    public Stream<Purchase> listPurchases(@RequestParam(value = "day", required = false) String day)
    {
        Stream<Purchase> purchaseStream;

        try {
            if(day!=null)
            {
                Date dateIni = new SimpleDateFormat("dd-MM-yyyy").parse(day);
                Date dateFin = new Date(dateIni.getYear(), dateIni.getMonth(), dateIni.getDate(), 23, 59, 59);
                purchaseStream = this.purchaseService.listSalesByDate(dateIni, dateFin);
            }
            else
            {
                purchaseStream = this.purchaseService.listPurchases();
            }
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return purchaseStream;
    }
}
