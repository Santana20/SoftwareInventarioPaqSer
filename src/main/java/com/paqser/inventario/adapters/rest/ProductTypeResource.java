package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.ProductType;
import com.paqser.inventario.domain.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(ProductTypeResource.PRODUCTTYPES)
public class ProductTypeResource {

    static final String PRODUCTTYPES = "/api/productTypes";

    private ProductTypeService productTypeService;

    @Autowired
    public ProductTypeResource(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping
    public ProductType createProductType(@RequestBody ProductType paramProductType) {
        ProductType productType;
        try {
            productType = this.productTypeService.createProductType(paramProductType);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return productType;
    }

    @GetMapping
    public Stream<ProductType> getAllProductTypes() {
        Stream<ProductType> productTypeStream;
        try {
            productTypeStream = this.productTypeService.listAllProductTypes();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return productTypeStream;
    }
}
