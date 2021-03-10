package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.DetailProduct;
import com.paqser.inventario.domain.services.DetailProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(DetailProductResource.DETAILPRODUCTS)
public class DetailProductResource {

    static final String DETAILPRODUCTS = "/api/detailProduct";
    static final String FINDALLDPBYIDPRODUCT = "/listDP/{idProduct}";

    private final DetailProductService detailProductService;

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

    @GetMapping(DetailProductResource.FINDALLDPBYIDPRODUCT)
    public Stream<DetailProduct> findAllDetailProductByIdProduct(@PathVariable("idProduct") Long idProduct)
    {
        Stream<DetailProduct> detailProductStream;
        try
        {
            detailProductStream = this.detailProductService.findAllDetailProductByIdProduct(idProduct);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return detailProductStream;
    }
}
