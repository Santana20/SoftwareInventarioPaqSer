package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.adapters.utils.DTOClass.DetailPurchasePDF;
import com.paqser.inventario.domain.services.DetailPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Stream;

@RestController
@RequestMapping(DetailPurchaseResource.DETAILPURCHASE)
public class DetailPurchaseResource {

    static final String DETAILPURCHASE = "/api/detailPurchase";
    static final String FINDALLDETAIPURCHASELBYIDPURCHASE = "/listDPu/{idPurchase}";

    private final DetailPurchaseService detailPurchaseService;

    @Autowired
    public DetailPurchaseResource(DetailPurchaseService detailPurchaseService) {
        this.detailPurchaseService = detailPurchaseService;
    }

    @GetMapping(DetailPurchaseResource.FINDALLDETAIPURCHASELBYIDPURCHASE)
    public Stream<DetailPurchasePDF> findAllDetailPurchaseByIdPurchase(@PathVariable("idPurchase") Long idPurchase)
    {
        Stream<DetailPurchasePDF> detailPurchaseStream;

        try
        {
            detailPurchaseStream = this.detailPurchaseService
                    .findAllDetailPurchaseByIdPurchase(idPurchase)
                    .map(DetailPurchasePDF::new);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return detailPurchaseStream;
    }
}
