package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.Brand;
import com.paqser.inventario.domain.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(BrandResource.BRANDS)
public class BrandResource {

    static final String BRANDS = "/api/brands";

    private BrandService brandService;

    @Autowired
    public BrandResource(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public Brand createBrand(@RequestBody Brand paramBrand) {
        Brand brand;
        try {
            brand = this.brandService.createBrand(paramBrand);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return brand;
    }

    @GetMapping
    public Stream<Brand> getAllBrands() {
        Stream<Brand> brandStream;
        try {
            brandStream = this.brandService.listAllBrands();
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return brandStream;
    }
}
