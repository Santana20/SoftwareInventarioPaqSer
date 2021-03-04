package com.paqser.inventario.adapters.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.paqser.inventario.adapters.utils.DTOClass.DetailSalePDF;
import com.paqser.inventario.adapters.utils.DTOClass.SalePDF;
import com.paqser.inventario.domain.models.Sale;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

public class SalePDFExporter {

    private final Stream<SalePDF> listSales;

    public SalePDFExporter(Stream<Sale> listSales) {
        this.listSales = listSales.map(SalePDF::new);
    }

    public void export(HttpServletResponse response) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, response.getOutputStream());

        documentPDF.open();

        documentPDF.add(new Paragraph("Lista de todas las ventas"));
        if (listSales != null)
        {
            listSales.forEach(salePDF -> {
                documentPDF.add(new Paragraph("Venta: #" + salePDF.getIdSale().toString()));
                documentPDF.add(new Paragraph("Dia: " + dateFormat.format(salePDF.getDateSale())));
                documentPDF.add(new Paragraph("Total: " + salePDF.getTotal().toString()));

                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                table.setSpacingBefore(10);

                writeTableHeader(table);
                writeTableData(table, salePDF.getDetailSalePDFList());
                documentPDF.add(table);
            });
        }
        documentPDF.close();
    }

    private void writeTableHeader(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Codigo"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descripcion"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Precio Unitario"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cantidad"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SubTotal"));
        table.addCell(cell);
    }
    private void writeTableData(PdfPTable table, List<DetailSalePDF> detailSaleList)
    {
        if (detailSaleList == null) return;
        for (DetailSalePDF detailSalePDF : detailSaleList)
        {
            table.addCell(detailSalePDF.getIdProduct());
            table.addCell(detailSalePDF.getDescription());
            table.addCell(String.valueOf(detailSalePDF.getUnitPrice()));
            table.addCell(String.valueOf(detailSalePDF.getCount()));
            table.addCell(String.valueOf(detailSalePDF.getSubTotal()));
        }
    }

}
