package com.paqser.inventario.adapters.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class SalePDFExporter {

    private Stream<Sale> listSales;

    public SalePDFExporter(Stream<Sale> listSales) {
        this.listSales = listSales;
    }

    public void export(HttpServletResponse response) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, response.getOutputStream());

        documentPDF.open();

        documentPDF.add(new Paragraph("Lista de todas las ventas"));
        if (listSales != null)
        {
            listSales.forEach(sale -> {
                documentPDF.add(new Paragraph("Venta: #" + sale.getIdSale().toString()));
                documentPDF.add(new Paragraph("Dia: " + dateFormat.format(sale.getDateSale())));
                documentPDF.add(new Paragraph("Total: " + sale.getTotal().toString()));

                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);

                writeTableHeader(table);
                writeTableData(table, sale.getDetailSaleList());
                documentPDF.add(table);
            });
        }
        documentPDF.close();
    }

    private void writeTableHeader(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Producto"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cantidad Pedida"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SubTotal"));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table, List<DetailSale> detailSaleList)
    {
        if (detailSaleList == null) return;
        for (DetailSale detailSale : detailSaleList)
        {
            table.addCell(String.valueOf(detailSale.getIdDetailProduct()));
            table.addCell(String.valueOf(detailSale.getSaleCount()));
            table.addCell(String.valueOf(detailSale.getSubTotal()));
        }
    }

}
