package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.services.DetailProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(DetailProductResource.DETAILPRODUCTS)
public class DetailProductResource {

    static final String DETAILPRODUCTS = "/api/detailProduct";

    private DetailProductService detailProductService;

    @Autowired
    public DetailProductResource(DetailProductService detailProductService) {
        this.detailProductService = detailProductService;
    }

    @PostMapping
    public DetailProduct createDetailProduct(@RequestBody DetailProduct paramDetailProduct) {
        DetailProduct detailProduct;
        try {
            detailProduct = this.detailProductService.createDetailProduct(paramDetailProduct);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return detailProduct;
    }
}
