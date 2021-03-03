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
    public void exportSalePdf(HttpServletResponse response,
                              @RequestParam(value = "day") String day)
    {
        try
        {
            response.setContentType("application/pdf");
            /*
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = sales_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);
            */
            Stream<Sale> listSales;
            if (day != null) {
                Date dateIni = new SimpleDateFormat("yyyy-MM-dd").parse(day);
                Date dateFin = new Date(dateIni.getYear(), dateIni.getMonth(), dateIni.getDate(), 23, 59, 59);
                listSales = this.saleService.listSalesByDate(dateIni, dateFin);
            }
            else listSales = this.saleService.listSales();

            SalePDFExporter exporter = new SalePDFExporter(listSales);

            exporter.export(response);
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
