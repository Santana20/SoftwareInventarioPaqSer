package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.domain.models.Product;
import com.paqser.inventario.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(ProductResource.PRODUCTS)
public class ProductResource {

    static final String PRODUCTS = "/api/product";
    static final String SEARCH = "/search";
    static final String IDPRODUCT = "/{idProduct}";

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product paramProduct) {
        Product product;
        try {
            product = this.productService.createProduct(paramProduct);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return product;
    }

    @GetMapping(SEARCH)
    public Stream<Product> searchProducts(@RequestParam(value = "name",          required = false) String name,
                                          @RequestParam(value = "idBrand",       required = false) Long idBrand,
                                          @RequestParam(value = "idProductType", required = false) Long idProductType) {

        Stream<Product> productStream;
        try {
            productStream = this.productService.searchProducts(name, idBrand, idProductType);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return productStream;

    }

    @GetMapping(SEARCH + IDPRODUCT)
    public Product getProductById(@PathVariable(value = "idProduct") String idProduct) {
        //Todo

        Product product;
        try {
            product = this.productService.getProductById(idProduct);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return product;
    }

}
