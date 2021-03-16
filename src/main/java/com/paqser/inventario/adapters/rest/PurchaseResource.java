package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.Purchase;
import com.paqser.inventario.domain.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(PurchaseResource.PURCHASE)
public class PurchaseResource {

    static final String PURCHASE = "/api/purchase";

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
}
