package com.paqser.inventario.adapters.rest;

import com.paqser.inventario.adapters.utils.SalePDFExporter;
import com.paqser.inventario.domain.models.Sale;
import com.paqser.inventario.domain.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@RestController
@RequestMapping(SaleResource.SALE)
public class SaleResource {

    static final String SALE = "/api/sale";
    static final String EXPORTPDF = "/export";
    static final String LISTSALES = "/list";

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
    @GetMapping(SaleResource.EXPORTPDF)
    public void exportInPDFSale(HttpServletResponse response, @RequestParam(value = "idSale") Long idSale) {
        try {
            response.setContentType("application/pdf");
            SalePDFExporter exporter = new SalePDFExporter();

            Sale sale = this.saleService.findSaleByIdSale(idSale);

            exporter.exportSale(response, sale);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(SaleResource.LISTSALES + SaleResource.EXPORTPDF)
    public void exportInPDFListSalesByDate(HttpServletResponse response, @RequestParam(value = "day") String day)
    {
        try
        {
            response.setContentType("application/pdf");
            Stream<Sale> listSales;
            if (day != null) {
                Date dateIni = new SimpleDateFormat("dd-MM-yyyy").parse(day);
                Date dateFin = new Date(dateIni.getYear(), dateIni.getMonth(), dateIni.getDate(), 23, 59, 59);
                listSales = this.saleService.listSalesByDate(dateIni, dateFin, true);
            }
            else listSales = this.saleService.listSales(true);

            SalePDFExporter exporter = new SalePDFExporter();

            exporter.exportListSales(response, listSales);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping(SaleResource.LISTSALES)
    public Stream<Sale> listSales(@RequestParam(value = "day", required = false) String day)
    {
        Stream<Sale> saleList;
        try {
            if(day!=null)
            {
                Date dateIni = new SimpleDateFormat("dd-MM-yyyy").parse(day);
                Date dateFin = new Date(dateIni.getYear(), dateIni.getMonth(), dateIni.getDate(), 23, 59, 59);
                saleList = this.saleService.listSalesByDate(dateIni, dateFin, false);
            }
            else
            {
                saleList = this.saleService.listSales(false);
            }
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return saleList;
    }
}
